package com.xinda.wx.wxmanager.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;

/**
 * 所有的DataSource配置信息
 *
 * @author lijie
 * @create 2018-10-17:07
 */

@Configuration
@Log4j2
@Order(1)
public class DataSourceConfig {

    @ConfigurationProperties(prefix = "spring.datasource.druid.datasource1")
    @Bean(name = "datasource1")
    @Primary
    public DataSource dataSource1() {
        return DruidDataSourceBuilder.create().build();
    }

    @ConfigurationProperties(prefix = "spring.datasource.druid.datasource2")
    @Bean(name = "datasource2")
    public DataSource dataSource2() {
        try {
            return DruidDataSourceBuilder.create().build();
        } catch (Exception e) {
            log.error("初始化数据库2失败 [dataSource2]");
        }
        return null;
    }

    @ConfigurationProperties(prefix = "mybatis-plus.global-config")
    @Bean
    public GlobalConfiguration globalConfiguration(MetaObjectHandler metaObjectHandler) {
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setMetaObjectHandler(metaObjectHandler);
        return globalConfiguration;
    }
}
