package com.xinda.wx.wxmanager.vo;

/**
 * 菜单key 唯一值
 *
 * @author lijie
 * @create 2019-13-0:03
 */
public enum MenuKey {
    REGISTER("register", "员工注册"),
    SALARY("salary", "薪资查询"),
    EMPLOY("employ", "员工信息"),
    COMPANY("company", "企业介绍");

    private String itemcode;
    private String name;

    MenuKey(String itemcode, String name) {
        this.itemcode = itemcode;
        this.name = name;
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
