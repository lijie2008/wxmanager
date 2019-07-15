/**
 * Copyright (C), 2019, 兆尹
 * FileName: EmployHandler
 * Author:   lijie
 * Date:     2019/7/13 0:17
 * Description: 员工信息handler
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
 * 〈员工信息handler〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
@Component
@Scope("prototype")
public class EmployHandler implements WxMessageHandler {

    @Override
    public WxXmlOutMessage handle(WxXmlMessage wxXmlMessage, Map<String, Object> map, IService iService) {
        return execute(wxXmlMessage);
    }

    private WxXmlOutMessage execute(WxXmlMessage wxMessage) {
        // TODO 调用查询员工基本信息方法
        return WxXmlOutMessage.TEXT()
                .content("帮助信息我啊")
                .toUser(wxMessage.getFromUserName())
                .fromUser(wxMessage.getToUserName()).build();
    }
}
