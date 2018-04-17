package com.zpj.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zpj.common.BaseController;
import com.zpj.sys.entity.User;
import com.zpj.sys.service.UserService;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
	@Autowired
	private UserService userService;
	
	private User user;
	
	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@RequestMapping("/checkLogin")
	public String checkLogin(ModelMap modelMap ,String username, String password){
//		String username=null, password=null;
		Map map=getRequestMap();
		user=(User)getSession().getAttribute("jluser");
		param=new HashMap<String,Object>();
		if(!isNotNullObject(username)){
			username="";
		}
		if(isNotNullObject(password)){
			password="";
		}
		if(null==user){
			if(!username.equalsIgnoreCase("")&&password.equalsIgnoreCase(""))
				user=userService.checkLogin(username,password);
		}
		modelMap.addAttribute("url", "/");
		if(null!=user){
			getSession().setAttribute("jluser", user);
			return "/sys/home/index";
		}else{
//			String path = request.getContextPath();
//			String basePath = request.getScheme() + "://"
//					+ request.getServerName() + ":" + request.getServerPort()
//					+ path + "/";
			return "forward:/login.jsp";
		}
	}
	
}
