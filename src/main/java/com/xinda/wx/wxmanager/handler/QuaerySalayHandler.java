/**
 * Copyright (C), 2019, 兆尹
 * FileName: QuaerySalayHandler
 * Author:   lijie
 * Date:     2019/7/13 0:39
 * Description: 查询工资处理类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.handler;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈查询工资处理类〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
@Component
@Scope("prototype")
public class QuaerySalayHandler implements WxMessageHandler {

    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) {
        return execute(wxXmlMessage);
    }

    private WxXmlOutMessage execute(WxXmlMessage wxXmlMessage) {
        // TODO 调用查询员工工资方法
        return WxXmlOutMessage.TEXT()
                .content("帮助信息我啊")
                .toUser(wxXmlMessage.getFromUserName())
                .fromUser(wxXmlMessage.getToUserName()).build();
    }
}
