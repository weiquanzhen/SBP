package com.sbp.app.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbp.app.dao.UserRepository;
import com.sbp.app.entity.User;
import com.sbp.app.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private UserRepository reposetory;
	
	@Override
	public boolean login(HttpServletRequest req, User user) {
		if(!LoginService.checkLogin(user))return false;
		HttpSession session = req.getSession();
		session.setAttribute("user", user);
		return true;
	}

	@Override
	public boolean regist(User user) {
		Integer findNum = reposetory.findUser(user.getPhone(), user.getEmail());
		if(findNum == 0) {
			reposetory.save(user);
			return true;
		}
		return false;
	}
	
}
