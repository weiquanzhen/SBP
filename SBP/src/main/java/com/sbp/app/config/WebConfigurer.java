package com.sbp.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sbp.app.intercepor.LoginInterceptor;

/**
 * - 整个服务的配置器，设置一些服务级相关配置
 * @author weiqz
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor intercepor;
	
	/**
	 * -对整个服务配置静态资源
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("");
	}
	/**
	 * -对整个服务配置拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(intercepor).addPathPatterns("/**").excludePathPatterns("/login","/regist");
	}
}
