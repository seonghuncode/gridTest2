package com.ysh.jqGri.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysh.jqGri.dto.UserDto;
import com.ysh.jqGri.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	
	@RequestMapping("/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@RequestMapping(value = "/doJoin", method = {RequestMethod.POST}, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String doJoin(UserDto userDto, HttpServletResponse response){
		
		System.out.println("userDto : " + userDto);
		System.out.println("userDto.getEmail : " + userDto.getEmail());
		System.out.println("userDto.getPassword : " + userDto.getPassword());
		
		userService.doJoin(userDto);
		
	
		return "<script>"
				+ "alert('회원가입이 완료 되었습니다.'); "
				+ "location.href='/loginForm';"
				+ "</script>";
	}
	

}
