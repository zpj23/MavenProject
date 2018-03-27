package com.totoro.data.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
public class TestController {

	
	@RequestMapping("vue")
	public String testVue(ModelMap modelMap){
		
		return "/test/vue";
	}
}
