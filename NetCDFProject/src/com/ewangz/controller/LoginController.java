package com.ewangz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test/*")
public class LoginController {
	
	@RequestMapping("login.do")
	public String login(String username, String password) {
		if(username.equals("admin") && password.equals("123456")) {
			return "success";
		}
		return "error";
	}
}
