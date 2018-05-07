package com.yezhihun.demo.interceptor;

import com.yezhihun.demo.util.AvailableDataSources;
import com.yezhihun.demo.util.DataSourceProvider;
import com.yezhihun.demo.util.ReadOnlyDataSource;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 只读库选择
 * @author tianye
 */
public class DatabaseInterceptor implements HandlerInterceptor {

    private static final Logger DB_INC_LOG = LoggerFactory.getLogger(DatabaseInterceptor.class);

    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        try {
            Method method = methodInvocation.getMethod();
            if (method != null && method.getAnnotation(ReadOnlyDataSource.class) != null) {
                DataSourceProvider.setDataSource(AvailableDataSources.READ);
            }
            return methodInvocation.proceed();
        } finally {
            DataSourceProvider.clearDataSource();
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        Method method = httpServletRequest.getMethod();
//        if (method != null && method.getAnnotation(ReadOnlyDataSource.class) != null) {
//            DataSourceProvider.setDataSource(AvailableDataSources.READ);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        DataSourceProvider.clearDataSource();
    }
}
