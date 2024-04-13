package com.ysh.jqGri.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestPageController {

	@RequestMapping("/mainPage")
	public String mainPage() {
		return "page/main";
	}
	
	@RequestMapping("/test1Page")
	public String test1Page() {
		return "page/test1Page";
	}
	
	@RequestMapping("/test2Page")
	public String test2Page() {
		return "page/test2Page";
	}
	
}
