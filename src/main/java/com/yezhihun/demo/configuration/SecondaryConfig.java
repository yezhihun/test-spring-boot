//package com.yezhihun.demo.configuration;
//
///**
// * Created by tianye on 2019/5/31.
// */
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import javax.persistence.EntityManager;
//import javax.sql.DataSource;
//import java.util.Map;
//
///**
// * 第二个数据源连接类，默认数据源
// */
////@Configuration
////@EnableTransactionManagement
////@EnableJpaRepositories(
////        entityManagerFactoryRef="entityManagerFactorySecondary",
////        transactionManagerRef="transactionManagerSecondary",
////        basePackages= { "com.cpic.repository.repository2" })//扫描jpa的sql语句的包
//public class SecondaryConfig {
//
//    @Autowired
//    private JpaProperties jpaProperties;
//
//    @Autowired
//    @Qualifier("secondaryDataSource")
//    private DataSource secondaryDataSource;
//
//    @Bean(name = "entityManagerSecondary")
//    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
//        return entityManagerFactorySecondary(builder).getObject().createEntityManager();
//    }
//
//    @Bean(name = "entityManagerFactorySecondary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary (EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(secondaryDataSource)
//                .packages("com.cpic.domain.test2")
//                .persistenceUnit("secondaryPersistenceUnit")
//                .properties(getVendorProperties())
//                .build();
//    }
//
//    //1.5.x版本的与2.0.x版本的配置这里不同
//    private Map<String, Object> getVendorProperties() {
//        /**
//         * springboot1.5.x版本的方式jpaProperties.getHibernateProperties(new DataSource());
//         * springboot2.0.x版本的阿方式jpaProperties.getHibernateProperties(new HibernateSettings());
//         */
//        return jpaProperties.getHibernateProperties(new HibernateSettings());
//    }
//
//    @Bean(name = "transactionManagerSecondary")
//    PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
//    }
//}
