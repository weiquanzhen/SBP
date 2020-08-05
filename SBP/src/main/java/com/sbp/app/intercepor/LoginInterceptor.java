package com.sbp.app.intercepor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sbp.app.entity.User;
import com.sbp.app.util.RedisUtil;
import com.sbp.app.util.SessionUtil;

/**
 * SpringBoot中拦截器需要实现 Handlerinterceptor接口 并重写其中的3个方法
 * preHandle
 * @author weiqz
 *
 */
@Component
public class LoginInterceptor implements HandlerInterceptor{
	@Autowired
	private SessionUtil sessionUtil;
	/**
	 * - 访问接口之前调用这个方法，通过实现WebMvcConfigurer并在addInterceptors方法中配置拦截哪些接口
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("准备校验用户的登陆状态...");
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null) {
			if(!sessionUtil.checkLogin(request)) {
				return false;
			}
		}
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
