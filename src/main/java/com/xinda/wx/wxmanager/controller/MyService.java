/**
 * Copyright (C), 2019, 兆尹
 * FileName: MyService
 * Author:   lijie
 * Date:     2019/7/13 1:27
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.controller;

import com.soecode.wxtools.api.WxService;
import com.soecode.wxtools.exception.WxErrorException;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
public class MyService extends WxService {

    @Override
    public String getAccessToken() throws WxErrorException {
        return "23_yNR9-6jB3z0QqFUiEXjEo-oGyT_X9UIKhIOz1NT-Pj6pB7pjgswuksReJQzCj8oQXWbflaf_8Id3tYoGckw" +
                "-eb9XYFbtMJtX0Mv4aLUu1nRt7qCLLW86hxHWH0AdqB8Lt2y4CW5_R_5ZmFATLDDhAAABTT";
        // return super.getAccessToken();
    }
}
