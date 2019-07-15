
package com.xinda.wx.wxmanager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = {HibernateJpaAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@ComponentScan(basePackages = {"com.xinda.wx"})
public class WxmanagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(WxmanagerApplication.class, args);
    }

}
