package com.yezhihun.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @ClassName: ExceptionHandler
 * @Description: 异常统一拦截处理
 *
 */
@Configuration
public class ExceptionHandler implements HandlerExceptionResolver {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView mv = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("code", 403);
		attributes.put("msg", "系统异常");
		logger.error("内部异常", ex);
		view.setAttributesMap(attributes);
		mv.setView(view);
		return mv;
	}

	@Bean
	public ExceptionHandler createExceptionHandler() {
		return new ExceptionHandler();
	}
}
