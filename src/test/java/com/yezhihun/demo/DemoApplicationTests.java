package com.yezhihun.demo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringRunner.class)
//@SpringBootTest
@ContextConfiguration
public class DemoApplicationTests {

//	@Autowired
//	DataSourceProperties dataSourceProperties;

	@Autowired
	ApplicationContext applicationContext;

	@ConfigurationProperties(prefix = "spring.datasource")
	@Bean
	public DataSource druid(){
		return  new DruidDataSource();
	}

	@Test
	public void contextLoads() {
		// 获取配置的数据源
		DataSource dataSource = druid();
		// 查看配置数据源信息
		System.out.println(dataSource);
		System.out.println(dataSource.getClass().getName());
//		System.out.println(dataSourceProperties);
		//执行SQL,输出查到的数据
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<?> resultList = jdbcTemplate.queryForList("select * from user");
		System.out.println("===>>>>>>>>>>>" + JSON.toJSONString(resultList));
	}


}
