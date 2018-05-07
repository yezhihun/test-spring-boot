package com.yezhihun.demo.configuration;

import com.yezhihun.demo.interceptor.DatabaseInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by tianye on 2018/5/4.
 */
@Configuration
public class MyInterceptor extends WebMvcConfigurerAdapter{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DatabaseInterceptor()).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}
