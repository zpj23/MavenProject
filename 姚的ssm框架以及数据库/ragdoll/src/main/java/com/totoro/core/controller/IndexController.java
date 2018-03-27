package com.totoro.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {

	
	@RequestMapping("/")
	public String toLogin(ModelMap modelMap){
		System.out.println("-------------toLogin-222-------------");
		return "/index/login";
	}
	
}
