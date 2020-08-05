package com.sbp.app.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbp.app.entity.User;
import com.sbp.app.service.LoginService;

@Component
public class SessionUtil {
	public static final String USER_SESSION_KEY = "user";// 从session中取User对象的key
	public static final String SESSION_COOKIE_NAME = "USER_SESSION_ID";
	public static final Integer DETFAULT_TIMEOUT = 60 * 30;// 30分钟超时 此参数用在设置redis的过期时间
	
	/**
	 * @return cookie中name对应的value值 没有对应name则返回null
	 */
	public static String getCookie(HttpServletRequest req, String name) {
		Cookie[] cookies = req.getCookies();
		if(cookies == null)return null;
		for (Cookie cookie : cookies) {
			if(cookie.getName().equalsIgnoreCase(name)) {
				return cookie.getValue();
			}
		}
		return null;
	}
}
