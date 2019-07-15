/**
 * Copyright (C), 2019, 兆尹
 * FileName: RegisterEmployHandler
 * Author:   lijie
 * Date:     2019/7/13 2:19
 * Description: 员工注册服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.xinda.wx.wxmanager.service.EmployService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈员工注册服务〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
@Component
@Scope("prototype")
public class RegisterEmployHandler implements WxMessageHandler {

    @Resource
    private EmployService employService;

    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) {
        return execute(wxXmlMessage);
    }

    private WxXmlOutMessage execute(WxXmlMessage wxXmlMessage) {
        return null;
    }
}
