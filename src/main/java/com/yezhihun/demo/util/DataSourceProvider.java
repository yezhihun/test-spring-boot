package com.yezhihun.demo.util;


public class DataSourceProvider {
    private static final ThreadLocal<AvailableDataSources> dataSourceHolder = new ThreadLocal<AvailableDataSources>();

    public static void setDataSource(final AvailableDataSources customerType) {
        dataSourceHolder.set(customerType);
    }

    public static AvailableDataSources getDataSource() {
        AvailableDataSources ads = (AvailableDataSources) dataSourceHolder.get();
        return ads;
    }

    public static void clearDataSource() {
        dataSourceHolder.remove();
    }

}
