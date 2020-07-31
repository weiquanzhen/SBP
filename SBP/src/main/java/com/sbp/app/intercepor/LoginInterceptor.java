package com.sbp.app.intercepor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sbp.app.entity.User;

/**
 * SpringBoot中拦截器需要实现 Handlerinterceptor接口 并重写其中的3个方法
 * preHandle
 * @author weiqz
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
	/**
	 * - 访问接口之前调用这个方法，通过实现WebMvcConfigurer并在addInterceptors方法中配置拦截哪些接口
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("准备拦截查询是否登陆");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null)
			System.out.println("没登陆");
		else
			System.out.println("已登录");
		return true;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
