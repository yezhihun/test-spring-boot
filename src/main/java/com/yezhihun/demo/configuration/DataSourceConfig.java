//package com.yezhihun.demo.configuration;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Primary;
//
//import javax.sql.DataSource;
//
///**
// * Created by tianye on 2019/5/31.
// */
////@Configuration
//public class DataSourceConfig {
//
//    /**
//     * 第一个数据源总装类
//     * @return
//     */
//    @Primary//表示为默认数据源，必须要有一个默认的数据源
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")//指定第一个数据源的名称
//    public DataSource primaryDataSource() {
//        System.out.println("第1个数据源连接成功！");
//        //1.5.x版本的与2.0.x版本的配置这里不同
//        /**
//         * springboot1.5.x版本的方式DataSourceBuilder.create().build();
//         * springboot2.0.x版本的阿方式primaryDataSourceProperties().initializeDataSourceBuilder().build();
//         */
//        return primaryDataSourceProperties().initializeDataSourceBuilder().build();
//    }
//
//
//    @Primary
//    @Bean(name = "primaryDataSourceProperties")
//    @Qualifier("primaryDataSourceProperties")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSourceProperties primaryDataSourceProperties() {
//        System.out.println("111111111111");
//        return new DataSourceProperties();
//    }
//
//    /**
//     * 第二个数据源总装类
//     * @return
//     */
//    @Bean(name = "secondaryDataSource")
//    @Qualifier("secondaryDataSource")
//    @ConfigurationProperties(prefix = "spring.read-datasource")//指定第二个数据源的名称
//    public DataSource secondaryDataSource() {
//        System.out.println("第2个数据源连接成功！");
//        //1.5.x版本的与2.0.x版本的配置这里不同
//        /**
//         * springboot1.5.x版本的方式DataSourceBuilder.create().build();
//         * springboot2.0.x版本的阿方式primaryDataSourceProperties().initializeDataSourceBuilder().build();
//         */
//        return secondaryDataSourceProperties().initializeDataSourceBuilder().build();
//    }
//
//    @Bean(name = "secondaryDataSourceProperties")
//    @Qualifier("secondaryDataSourceProperties")
//    @ConfigurationProperties(prefix = "spring.read-datasource")
//    public DataSourceProperties secondaryDataSourceProperties() {
//        System.out.println("222222222222222222");
//        return new DataSourceProperties();
//    }
//}