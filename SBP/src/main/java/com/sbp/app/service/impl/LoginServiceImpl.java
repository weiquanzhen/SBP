package com.sbp.app.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.sbp.app.dao.UserRepository;
import com.sbp.app.entity.User;
import com.sbp.app.service.LoginService;
import com.sbp.app.util.RedisUtil;
import com.sbp.app.util.SessionUtil;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository reposetory;
	
	@Autowired
	private RedisUtil redis;
	
	@Override
	public void login(HttpServletRequest req, HttpServletResponse resp, User user) {
		if(!checkLogin(req)) {
			saveLoginStatus(req, resp, user);
		}
	}
	
	@Override
	public void saveLoginStatus(HttpServletRequest req, HttpServletResponse resp, User user) {
		HttpSession session = req.getSession();
		session.setAttribute(SessionUtil.USER_SESSION_KEY, user);
		String jSessionId = null;
		if((jSessionId = SessionUtil.getCookie(req, SessionUtil.SESSION_COOKIE_NAME)) == null) {
			jSessionId = session.getId();
			resp.addCookie(new Cookie(SessionUtil.SESSION_COOKIE_NAME, jSessionId));
		}
		redis.setEx(jSessionId, JSONObject.toJSONString(user), SessionUtil.DETFAULT_TIMEOUT);
	}
	
	@Override
	public void saveLoginSession(HttpServletRequest req, User user) {
		req.getSession().setAttribute(SessionUtil.USER_SESSION_KEY, user);
	}

	/**
	 * 校验用户是否已登录，如果是，延长用户登录信息的寿命
	 * @return true-登陆/false-没有登陆
	 */
	@Override
	public boolean checkLogin(HttpServletRequest req) {
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute(SessionUtil.USER_SESSION_KEY);
		if(user == null) {// session中没有用户信息
			String sessionId = null;
			if((sessionId = SessionUtil.getCookie(req, SessionUtil.SESSION_COOKIE_NAME)) != null) {// 看看cookie中有没有保存sessionid
				// 找到sessionid
				if((user = redis.getStringObject(sessionId, User.class)) != null) {// 根据sessionid找redis中用户数据
					// 找到用户数据
					saveLoginSession(req, user);
					redis.expire(sessionId, RedisUtil.DEFUAULT_LOGIN_TIMEOUT);// 更新数据寿命
					return true;
				}
			}
		}else {
			// 用户信息已经保存在session作用域中
			return true;
		}
		// 1.用户没有登陆
		// 2.用户登陆信息过期，在session中、redis中都没有找到用户信息
		return false;
	}
	
	/**
	 * 类锁，在当前服务中是有效的，但是在微服务架构中，多服务模块之间是隔离的，所以synchronized会失效
	 * 解决方案：分布式锁，通过监听Zookeeper的znode状态判断是否有锁
	 */
	@Override
	public synchronized boolean regist(User user) {
		Integer findNum = reposetory.findUser(user.getPhone(), user.getEmail());
		if(findNum == 0) {
			reposetory.save(user);
			return true;
		}
		return false;
	}
	
}
