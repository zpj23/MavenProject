package com.zpj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpj.common.BaseController;
@Controller
@RequestMapping("/home")
public class HomeController extends BaseController{
	
	@RequestMapping("/toSy")
	public String toSy(){
		return "/sys/home/sy";
	}
	
}
