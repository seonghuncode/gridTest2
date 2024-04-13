package com.ysh.jqGri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}

}
