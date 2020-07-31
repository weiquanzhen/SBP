package com.sbp.app.controller.common;

import javax.servlet.http.HttpServletRequest;

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
	public ResponseDate login(HttpServletRequest req, User user) {
		ResponseDate r = new ResponseDate();
		if(service.login(req, user)) {
			System.out.println(req.getSession().getAttribute("user"));
			return r;
		}
		return r;
	}
	@GetMapping("/regist")
	public ResponseDate regist(User user) {
		ResponseDate r = new ResponseDate();
		if(service.regist(user)) {
			r.setMessage("注册成功");
			return r;
		}
		r.setCode(1);
		r.setMessage("注册失败，账户已存在");
		return r;
	}
}
