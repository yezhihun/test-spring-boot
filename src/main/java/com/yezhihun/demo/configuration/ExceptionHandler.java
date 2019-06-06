package com.yezhihun.demo.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 * @ClassName: ExceptionHandler
 * @Description: 异常统一拦截处理
 *
 */
@ControllerAdvice
public class ExceptionHandler {
	private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	// 声明要捕获的异常
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	@ResponseBody
	public ModelAndView defultExcepitonHandler(HttpServletRequest request, Exception e) {
		ModelAndView mv = new ModelAndView();
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		Map<String, Object> attributes = new HashMap<>();
		attributes.put("code", 403);
		attributes.put("msg", "系统异常");
		logger.error("内部异常", e);
		view.setAttributesMap(attributes);
		mv.setView(view);
		return mv;
	}
}
