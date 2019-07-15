package com.xinda.wx.wxmanager.config;

import java.lang.annotation.*;

/**
 * 切换数据源注解
 *
 * @author lijie
 * @create 2018-10-15:36
 */

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SwitchDataSource {
    DataSourceType type() default DataSourceType.datasource1;
}
