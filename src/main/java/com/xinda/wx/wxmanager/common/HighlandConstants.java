/**
 * Copyright (C), 2019, 兆尹
 * FileName: HighlandConstants
 * Author:   lijie
 * Date:     2019/7/13 11:19
 * Description: 加密常量信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.common;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 〈一句话功能简述〉<br>
 * 〈加密常量信息〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
public class HighlandConstants {
    // 加密token
    public static final String BIND_TOKEN = "xinda_|001";

    // 存储相应的openid 和 签名之间的关系
    public static final Map<String, String> MAP_OPENID_SIGNATURE = new ConcurrentHashMap<>();
}
