package com.yezhihun.demo.configuration;

import com.yezhihun.demo.util.DynamicDataSourceContextHolder;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianye on 2018/5/7.
 */
@Configuration
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
    /**
     * 配置上下文（也可以理解为配置文件的获取工具）
     */
    private Environment evn;

    /**
     * 别名
     */
    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

    /**
     * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
     */
    static {
        aliases.addAliases("url", new String[]{"jdbc-url"});
        aliases.addAliases("username", new String[]{"user"});
    }

    /**
     * 存储我们注册的数据源
     */
    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    /**
     * 参数绑定工具 springboot2.0新推出
     */
    private Binder binder;

    /**
     * ImportBeanDefinitionRegistrar接口的实现方法，通过该方法可以按照自己的方式注册bean
     *
     * @param annotationMetadata
     * @param beanDefinitionRegistry
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        // 获取所有数据源配置
        Map config, defauleDataSourceProperties;
        defauleDataSourceProperties = binder.bind("spring.datasource.master", Map.class).get();
        // 获取数据源类型
        String typeStr = evn.getProperty("spring.datasource.master.type");
        // 获取数据源类型
        Class<? extends DataSource> clazz = getDataSourceType(typeStr);
        // 绑定默认数据源参数 也就是主数据源
        DataSource defaultDatasource = bind(clazz, defauleDataSourceProperties);
        DynamicDataSourceContextHolder.dataSourceIds.add("master");
        logger.info("注册默认数据源成功");
        // 获取其他数据源配置
        Map customDataSourceProperties = binder.bind("spring.datasource.slave", Map.class).get();
        // 获取数据源类型
        String customTypeStr = evn.getProperty("spring.datasource.slave.type");
        // 获取数据源类型
        Class<? extends DataSource> customClazz = getDataSourceType(customTypeStr);
        // 绑定默认数据源参数 也就是主数据源
        DataSource consumerDatasource = bind(customClazz, customDataSourceProperties);
        String key = evn.getProperty("spring.datasource.slave.key");
        DynamicDataSourceContextHolder.dataSourceIds.add(key);
        customDataSources.put(key, consumerDatasource);
        logger.info("注册读数据源成功");

//        List<Map> configs = binder.bind("spring.datasource.cluster", Bindable.listOf(Map.class)).get();
//        // 遍历从数据源
//        for (int i = 0; i < configs.size(); i++) {
//            config = configs.get(i);
//            clazz = getDataSourceType((String) config.get("type"));
//            defauleDataSourceProperties = config;
//            // 绑定参数
//            consumerDatasource = bind(clazz, defauleDataSourceProperties);
//            // 获取数据源的key，以便通过该key可以定位到数据源
//            String key = config.get("key").toString();
//            customDataSources.put(key, consumerDatasource);
//            // 数据源上下文，用于管理数据源与记录已经注册的数据源key
//            DynamicDataSourceContextHolder.dataSourceIds.add(key);
//            logger.info("注册数据源{}成功", key);
//        }
        // bean定义类
        GenericBeanDefinition define = new GenericBeanDefinition();
        // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(DynamicDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv = define.getPropertyValues();
        // 添加默认数据源，避免key不存在的情况没有数据源可用
        mpv.add("defaultTargetDataSource", defaultDatasource);
        // 添加其他数据源
        mpv.add("targetDataSources", customDataSources);
        // 将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("datasource", define);
        logger.info("注册数据源成功，一共注册{}个数据源", customDataSources.keySet().size() + 1);
    }

    /**
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
    private Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) {
                // 字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                // 默认为hikariCP数据源，与springboot默认数据源保持一致
                type = HikariDataSource.class;
            }
            return type;
        } catch (Exception e) {
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr); //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
        }
    }

    /**
     * 绑定参数，以下三个方法都是参考DataSourceBuilder的bind方法实现的，目的是尽量保证我们自己添加的数据源构造过程与springboot保持一致
     *
     * @param result
     * @param properties
     */
    private void bind(DataSource result, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        // 将参数绑定到对象
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(result));
    }

    private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        // 通过类型绑定参数并获得实例对象
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }

    /**
     * @param clazz
     * @param sourcePath 参数路径，对应配置文件中的值，如: spring.datasource
     * @param <T>
     * @return
     */
    private <T extends DataSource> T bind(Class<T> clazz, String sourcePath) {
        Map properties = binder.bind(sourcePath, Map.class).get();
        return bind(clazz, properties);
    }

    /**
     * EnvironmentAware接口的实现方法，通过aware的方式注入，此处是environment对象
     *
     * @param environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        logger.info("开始注册数据源");
        this.evn = environment;
        // 绑定配置器
        binder = Binder.get(evn);
    }
//
////    @Value("${spring.datasource.url}")
////    private String url1;
//
//    //如配置文件中未指定数据源类型，使用该默认值
//    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";
//    private ConversionService conversionService = new DefaultConversionService();
//    private PropertyValues dataSourcePropertyValues;
//
//    // 默认数据源
//    private DataSource defaultDataSource;
//
//    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();
//
//    @Override
//    public void setEnvironment(Environment environment) {
//        System.out.println("DynamicDataSourceRegister.setEnvironment()");
//        initDefaultDataSource(environment);
//        initCustomDataSources(environment);
//    }
//
//    @Override
//    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
//        System.out.println("DynamicDataSourceRegister.registerBeanDefinitions()");
//        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
//        // 将主数据源添加到更多数据源中
//        targetDataSources.put("dataSource", defaultDataSource);
//        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
//        // 添加更多数据源
//        targetDataSources.putAll(customDataSources);
//        for (String key : customDataSources.keySet()) {
//            DynamicDataSourceContextHolder.dataSourceIds.add(key);
//        }
//
//        // 创建DynamicDataSource
//        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//        beanDefinition.setBeanClass(DynamicDataSource.class);
//
//        beanDefinition.setSynthetic(true);
//        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
//        //添加属性：AbstractRoutingDataSource.defaultTargetDataSource
//        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
//        mpv.addPropertyValue("targetDataSources", targetDataSources);
//        beanDefinitionRegistry.registerBeanDefinition("dataSource", beanDefinition);
//    }
//
//    /**
//     * 加载主数据源配置.
//     */
//    private void initDefaultDataSource(Environment env) {
//        //创建数据源;
//        defaultDataSource = primaryDataSource();
//        dataBinder(defaultDataSource, env);
//    }
//
//    /**
//     * 初始化更多数据源
//     */
//    private void initCustomDataSources(Environment env) {
//        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
//        String dsPrefix = "read";
//        DataSource ds = secondaryDataSource();
//        customDataSources.put(dsPrefix, ds);
//        DynamicDataSourceContextHolder.dataSourceIds.add(dsPrefix);
//        dataBinder(ds, env);
//    }
//
//    /**
//     * 创建datasource.
//     */
//    @SuppressWarnings("unchecked")
//    public DataSource buildDataSource(Map<String, Object> dsMap) {
//        Object type = dsMap.get("type");
//        if (type == null) {
//            type = DATASOURCE_TYPE_DEFAULT;// 默认DataSource
//        }
//        Class<? extends DataSource> dataSourceType;
//
//        try {
//            dataSourceType = (Class<? extends DataSource>) Class.forName((String) type);
//            String driverClassName = dsMap.get("driver-class-name").toString();
//            String url = dsMap.get("url").toString();
//            String username = dsMap.get("username").toString();
//            String password = dsMap.get("password").toString();
//            DataSourceBuilder factory = DataSourceBuilder.create().driverClassName(driverClassName).url(url).username(username).password(password).type(dataSourceType);
//            return factory.build();
////            return primaryDataSourceProperties().initializeDataSourceBuilder().build();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 为DataSource绑定更多数据
//     */
//    private void dataBinder(DataSource dataSource, Environment env) {
//        Binder binder
//        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
//        dataBinder.setConversionService(conversionService);
//        dataBinder.setIgnoreNestedProperties(false);//false
//        dataBinder.setIgnoreInvalidFields(false);//false
//        dataBinder.setIgnoreUnknownFields(true);//true
//
//        if (dataSourcePropertyValues == null) {
//            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "spring.datasource").getSubProperties(".");
//            Map<String, Object> values = new HashMap<>(rpr);
//            // 排除已经设置的属性
//            values.remove("type");
//            values.remove("driverClassName");
//            values.remove("url");
//            values.remove("username");
//            values.remove("password");
//            dataSourcePropertyValues = new MutablePropertyValues(values);
//        }
//        dataBinder.bind(dataSourcePropertyValues);
//    }
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
}
