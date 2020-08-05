package com.sbp.app.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sbp.app.entity.ResponseDate;
import com.sbp.app.entity.User;
import com.sbp.app.service.LoginService;

@RestController
public class LoginController {
	@Autowired
	private LoginService service;
	
	@GetMapping("/login")
	public ResponseDate login(HttpServletRequest req, HttpServletResponse resp, User user) {
		ResponseDate r = new ResponseDate();
		service.login(req, resp, user);
		r.setMessage("login success!");
		return r;
	}
	@GetMapping("/regist")
	public ResponseDate regist(User user) {
		ResponseDate r = new ResponseDate();
		if(service.regist(user)) {
			r.setMessage("regist success!");
			return r;
		}
		r.setCode(1);
		r.setMessage("account is exists");
		return r;
	}
}
