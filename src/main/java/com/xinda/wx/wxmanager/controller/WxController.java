package com.xinda.wx.wxmanager.controller;


import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.api.WxMessageRouter;
import com.soecode.wxtools.bean.WxUserList;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.bean.result.WxOAuth2AccessTokenResult;
import com.soecode.wxtools.exception.AesException;
import com.soecode.wxtools.exception.WxErrorException;
import com.soecode.wxtools.util.StringUtils;
import com.soecode.wxtools.util.crypto.SHA1;
import com.soecode.wxtools.util.xml.XStreamTransformer;
import com.xinda.wx.wxmanager.entity.Employ;
import com.xinda.wx.wxmanager.handler.EmployHandler;
import com.xinda.wx.wxmanager.handler.QuaerySalayHandler;
import com.xinda.wx.wxmanager.handler.QuerySalaryMatcher;
import com.xinda.wx.wxmanager.handler.RegisterEmployHandler;
import com.xinda.wx.wxmanager.service.EmployService;
import com.xinda.wx.wxmanager.vo.MenuKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static com.xinda.wx.wxmanager.common.HighlandConstants.BIND_TOKEN;
import static com.xinda.wx.wxmanager.common.HighlandConstants.MAP_OPENID_SIGNATURE;

/**
 * <p>
 * 学员表 前端控制器
 * </p>
 *
 * @author Lios123
 * @since 2019-07-12
 */
@RestController
@RequestMapping("/wx")
@Log4j2
public class WxController {

    @Resource
    private IService iService;

    @Resource
    private WxMessageRouter router;

    @Resource
    private EmployHandler employHandler;

    @Resource
    private QuaerySalayHandler quaerySalayHandler;

    @Resource
    private RegisterEmployHandler registerEmployHandler;

    @Resource
    private EmployService employService;

    @GetMapping
    /**
     * 功能描述: <br>
     * 〈验证服务器的有效性〉
     *
     * @Param:[signature, timestamp, nonce, echostr]
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:lijie
     * @Date: 2019/7/12 23:38
     */
    public String validate(String signature, String timestamp, String nonce, String echostr) {
        if (iService.checkSignature(signature, timestamp, nonce, echostr)) {
            return echostr;
        }
        return null;
    }

    @PostMapping
    public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        try {
            // 微信服务器推送过来的是XML格式 消息解析
            WxXmlMessage wx = XStreamTransformer.fromXml(WxXmlMessage.class, request.getInputStream());
            log.info("消息：\n " + wx.toString());
            // TODO msgID 排重

            // 简单匹配 员工输入查询工资
            router.rule().msgType(WxConsts.XML_MSG_TEXT).matcher(new QuerySalaryMatcher()).handler(quaerySalayHandler).next()
                    // 查询员工信息
                    .rule().event(WxConsts.EVT_CLICK).eventKey(MenuKey.EMPLOY.getItemcode()).handler(employHandler).next()
                    // 员工注册服务
                    .rule().event(WxConsts.EVT_VIEW).eventKey(MenuKey.REGISTER.getItemcode()).handler(registerEmployHandler).end();


            // 把消息传递给路由器进行处理
            WxXmlOutMessage xmlOutMsg = router.route(wx);

            if (xmlOutMsg != null) {
                // 因为是明文，所以不用加密，直接返回给用户
                out.print(xmlOutMsg.toXml());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }


    @GetMapping(value = "/register")
    public ModelAndView callback(@RequestParam(value = "code", required = false) String code) throws WxErrorException {
        log.info("code的值:" + code);

        if (!StringUtils.isBlank(code)) {
            ModelAndView modelAndView = new ModelAndView();

            WxOAuth2AccessTokenResult wxOAuth2AccessTokenResult = iService.oauth2ToGetAccessToken(code);
            String openid = wxOAuth2AccessTokenResult.getOpenid();

            // 获取微信用户所有信息
            WxUserList.WxUser.WxUserGet wxUserGet = new WxUserList.WxUser.WxUserGet();
            wxUserGet.setLang("zh_CN");
            wxUserGet.setOpenid(openid);
            WxUserList.WxUser userInfoByOpenId = iService.getUserInfoByOpenId(wxUserGet);

            // 业务逻辑 判断当前客户是否已经绑定过
            Employ employ = employService.selectById(openid);

            // 已绑定 跳转绑定过的页面
            if (employ != null) {
                log.info("跳转的用户查看用户信息页面");
                modelAndView.setViewName("mydata");
                return modelAndView;
            }

            // 未绑定过 需要先获取当前微信用户的信息 然后跳转到给定的注册页面
            String timeStamp = String.valueOf(System.currentTimeMillis());
            String signature = "";

            try {
                signature = SHA1.getSHA1(BIND_TOKEN, timeStamp, openid, "");
                MAP_OPENID_SIGNATURE.put(openid, signature);
            } catch (AesException e1) {
                e1.printStackTrace();
                log.error("用户签名加密信息出错");
            }
            modelAndView.setViewName("bind");
            Map<String, Object> map = new HashMap<>();
            map.put("timeStamp", timeStamp);
            map.put("openid", openid);
            map.put("signature", signature);
            modelAndView.addAllObjects(map);
            return modelAndView;
        } else {
            log.info("未获取到CODE");
        }

        return null;
    }
}

