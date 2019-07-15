/**
 * Copyright (C), 2019, 兆尹
 * FileName: wxManagerConfig
 * Author:   lijie
 * Date:     2019/7/12 23:36
 * Description: 微信基础配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.config;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageRouter;
import com.xinda.wx.wxmanager.controller.MyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 〈一句话功能简述〉<br>
 * 〈微信基础配置〉
 *
 * @author lijie
 * @create 2019/7/12
 * @since 1.0.0
 */
@Configuration
public class wxManagerConfig {

    @Bean
    public IService wxService() {
        return new MyService();
    }

    @Bean
    public WxMessageRouter wxMessageRouter(IService iService) {
        return new WxMessageRouter(iService);
    }

}
