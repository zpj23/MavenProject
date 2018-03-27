package com.zpj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.sys.service.UserService;
@Controller
@RequestMapping("userinfo")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	
	public void userList(String param){
		MyPage pagedata =userService.findPageData(param,page,limit);		
		this.jsonWrite2(pagedata);
	}
	
	
	
}
