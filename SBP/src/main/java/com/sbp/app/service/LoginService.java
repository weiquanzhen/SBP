package com.sbp.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbp.app.entity.User;

public interface LoginService {
	void login(HttpServletRequest req, HttpServletResponse resp, User user);
	boolean regist(User user);
	void saveLoginStatus(HttpServletRequest req, HttpServletResponse resp, User user);
	void saveLoginSession(HttpServletRequest req, User user);
	boolean checkLogin(HttpServletRequest req);
}
