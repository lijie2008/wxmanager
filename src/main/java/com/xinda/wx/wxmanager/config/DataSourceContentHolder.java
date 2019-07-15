package com.xinda.wx.wxmanager.config;

/**
 * 保存一个线程安全的DatabaseType容器
 *
 * @author lijie
 * @create 2018-10-13:52
 */

public class DataSourceContentHolder {

    private static final ThreadLocal<DataSourceType> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * AOP 设置当前数据源的
     *
     * @param type
     */
    public static void setDataBaseType(DataSourceType type) {
        CONTEXT_HOLDER.set(type);
    }

    /**
     * 获取当前线程数据源
     *
     * @return
     */

    public static DataSourceType getDatabaseType() {
        DataSourceType dataSourceType = CONTEXT_HOLDER.get();
        return dataSourceType;
    }

    /**
     * 清楚当前线程数据源 使用默认数据源
     */
    public static void clear() {
        CONTEXT_HOLDER.remove();
    }
}

