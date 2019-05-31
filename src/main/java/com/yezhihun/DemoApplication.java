package com.yezhihun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.yezhihun.demo.configuration.DynamicDataSourceRegister;

@SpringBootApplication
@ComponentScan(basePackages = "com.yezhihun")
@PropertySource(value = "config/app.properties")
@Import({DynamicDataSourceRegister.class})
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		//田业是傻逼，可大呢发放
	}
}
