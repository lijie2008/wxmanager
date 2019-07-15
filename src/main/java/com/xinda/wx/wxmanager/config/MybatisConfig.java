package com.xinda.wx.wxmanager.config;

import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * mybatis配置类
 *
 * @author lijies
 */
@Configuration
@MapperScan(basePackages = {"com.xinda.wx.wxmanager.dao"})
@Slf4j
public class MybatisConfig {

    @Autowired
    private Environment environment;

    /**
     * 功能描述:
     * mybatis plus分页拦截器
     *
     * @Param:
     * @return:
     * @since: 1.0.0
     * @Author:lijie
     * @Date: 2019/7/12 16:43
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 功能描述: <br>
     * 〈mybatis性能优化〉
     *
     * @Param:
     * @return:
     * @since: 1.0.0
     * @Author:lijie
     * @Date: 2019/7/12 17:00
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        /*<!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->*/
        performanceInterceptor.setMaxTime(1000);
        /*<!--SQL是否格式化 默认false-->*/
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("datasource1") DataSource dataSource1,
                                               @Qualifier("datasource2") DataSource dataSource2) {

        // 所有的数据源集合
        Map<Object, Object> targetDataSource = new HashMap<>(3);

        targetDataSource.put(DataSourceType.datasource1, dataSource1);
        targetDataSource.put(DataSourceType.datasource2, dataSource2);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);

        // 默认的数据源
        dynamicDataSource.setDefaultTargetDataSource(dataSource1);

        return dynamicDataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds,
                                               PaginationInterceptor paginationInterceptor,
                                               GlobalConfiguration globalConfiguration,
                                               DatabaseIdProvider databaseIdProvider) throws
            Exception {
        MybatisSqlSessionFactoryBean fb = new MybatisSqlSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setDatabaseIdProvider(databaseIdProvider);
        fb.setTypeAliasesPackage(environment.getProperty("mybatis.type-aliases-package"));
        fb.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources(environment.getProperty("mybatis" +
                        ".mapperLocations")));
        fb.setPlugins(new Interceptor[]{paginationInterceptor});
        fb.setConfigLocation(new ClassPathResource(environment.getProperty("mybatis.config-location")));
        //全局参数
        fb.setGlobalConfig(globalConfiguration);
        fb.setVfs(com.baomidou.mybatisplus.spring.boot.starter.SpringBootVFS.class);
        return fb.getObject();
    }

    /**
     * 配置当前数据源的事务管理器
     *
     * @param dataSource
     * @return
     */
    @Bean(name = "txManagerDS")
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DynamicDataSource
                                                                   dataSource) {
        DataSourceTransactionManager tm = new DataSourceTransactionManager(dataSource);
        return tm;
    }

    @Bean
    public DatabaseIdProvider getDatabaseIdProvider() {
        DatabaseIdProvider databaseIdProvider = new VendorDatabaseIdProvider();
        Properties properties = new Properties();
        properties.setProperty("DB2", "db2");
        properties.setProperty("Oracle", "oracle");
        properties.setProperty("MySQL", "mysql");
        databaseIdProvider.setProperties(properties);
        return databaseIdProvider;
    }
}
