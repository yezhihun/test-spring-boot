package com.yezhihun.demo.configuration;

import com.yezhihun.demo.util.DynamicDataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by tianye on 2018/5/7.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /*
     * 代码中的determineCurrentLookupKey方法取得一个字符串，
     * 该字符串将与配置文件中的相应字符串进行匹配以定位数据源，配置文件，即applicationContext.xml文件中需要要如下代码：(non-Javadoc)
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
