package com.xinda.wx.wxmanager.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态数据源
 *
 * @author lijie
 * @create 2018-10-13:55
 */

public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContentHolder.getDatabaseType();
    }
}
