/**
 * Copyright (C), 2019, 兆尹
 * FileName: CreateMeauRun
 * Author:   lijie
 * Date:     2019/7/13 0:57
 * Description: 服务启动创建菜单
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xinda.wx.wxmanager.controller;

import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxConsts;
import com.soecode.wxtools.bean.WxMenu;
import com.soecode.wxtools.exception.WxErrorException;
import com.xinda.wx.wxmanager.vo.MenuKey;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈服务启动创建菜单〉
 *
 * @author lijie
 * @create 2019/7/13
 * @since 1.0.0
 */
@Component
@Log4j2
public class CreateMeauRun implements CommandLineRunner {
    @Resource
    private IService iService;

    private String redirectUrl = "http://47.95.198.204/wxmanager/wx/register";

    @Override
    public void run(String... args) throws Exception {

        WxMenu menu = new WxMenu();
        List<WxMenu.WxMenuButton> btnList = new ArrayList<>();

        //员工相关信息
        WxMenu.WxMenuButton btn1 = new WxMenu.WxMenuButton();
        btn1.setName("员工");
        List<WxMenu.WxMenuButton> subList = new ArrayList<>();
        WxMenu.WxMenuButton btn1_1 = new WxMenu.WxMenuButton();
        btn1_1.setType(WxConsts.MENU_BUTTON_CLICK);
        btn1_1.setKey(MenuKey.EMPLOY.getItemcode());
        btn1_1.setName("员工信息");
        WxMenu.WxMenuButton btn1_2 = new WxMenu.WxMenuButton();
        btn1_2.setType(WxConsts.MENU_BUTTON_CLICK);
        btn1_2.setKey(MenuKey.SALARY.getItemcode());
        btn1_2.setName("最新工资");
        WxMenu.WxMenuButton btn1_3 = new WxMenu.WxMenuButton();
        btn1_3.setType(WxConsts.MENU_BUTTON_VIEW);
        btn1_3.setKey(MenuKey.REGISTER.toString());
        // 注册回调的URL
        btn1_3.setUrl(iService.oauth2buildAuthorizationUrl(redirectUrl, "snsapi_userinfo", "index"));
        btn1_3.setName("员工注册");

        subList.addAll(Arrays.asList(btn1_1, btn1_2, btn1_3));
        btn1.setSub_button(subList);

        // 企业信息
        WxMenu.WxMenuButton btn2 = new WxMenu.WxMenuButton();
        btn2.setType(WxConsts.MENU_BUTTON_CLICK);
        btn2.setKey(MenuKey.COMPANY.getItemcode());
        btn2.setName("企业介绍");

        //将三个按钮设置进btnList
        btnList.add(btn1);
        btnList.add(btn2);

        //设置进菜单类
        menu.setButton(btnList);

        //调用API即可
        try {
            //参数1--menu  ，参数2--是否是个性化定制。如果是个性化菜单栏，需要设置MenuRule
            iService.createMenu(menu, false);
        } catch (WxErrorException e) {
            e.printStackTrace();
            log.error("创建菜单失败");
        }
    }
}
