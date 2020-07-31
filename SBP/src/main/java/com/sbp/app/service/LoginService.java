package com.sbp.app.service;

import javax.servlet.http.HttpServletRequest;

import com.sbp.app.entity.User;

public interface LoginService {
	boolean login(HttpServletRequest req, User user);
	boolean regist(User user);
	static boolean checkLogin(User user) {
		return true;
	}
}
