/**
 * Copyright (C), 2019, 兆尹
 * FileName: QuerySalaryMatcher
 * Author:   lijie
 * Date:     2019/7/13 0:37
 * Description: 匹配查询工资关键字
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.handler;

import com.baomidou.mybatisplus.toolkit.StringUtils;
import com.soecode.wxtools.api.WxMessageMatcher;
import com.soecode.wxtools.bean.WxXmlMessage;

/**
 * 〈一句话功能简述〉<br> 
 * 〈匹配查询工资关键字〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
public class QuerySalaryMatcher implements WxMessageMatcher {

    @Override
    public boolean match(WxXmlMessage message) {
        if(StringUtils.isNotEmpty(message.getContent())){
            if(message.getContent().equals("查询工资")){
                return true;
            }
        }
        return false;
    }
}
