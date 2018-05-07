package com.yezhihun.demo.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tianye on 2018/5/7.
 */
public class DynamicDataSourceContextHolder {
    /*
    * 当使用ThreadLocal维护变量时，ThreadLocal为每个使用该变量的线程提供独立的变量副本，
    * 所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
    */
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /*
     * 管理所有的数据源id;
     * 主要是为了判断数据源是否存在;
     */
    public static List<String> dataSourceIds = new ArrayList<>();


    /**
     * 使用setDataSourceType设置当前的
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }


    /**
     * 获取当前线程中的数据源
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     * 删除当前线程池中的数据源
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }

    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}
