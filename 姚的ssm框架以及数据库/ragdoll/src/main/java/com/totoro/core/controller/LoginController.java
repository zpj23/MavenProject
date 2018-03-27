package com.totoro.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totoro.core.model.MenuInfo;
import com.totoro.core.model.UserInfo;
import com.totoro.core.service.MenuService;
import com.totoro.core.service.UserService;
import com.totoro.core.utils.MyController;


@Controller
@RequestMapping("login")
public class LoginController extends MyController{

	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;
	
	
	@RequestMapping("/")
	public String login(ModelMap modelMap){
		
		return "/index/login";
	}
	
	
	@RequestMapping("checkLogin")
	public String checkLogin(HttpServletRequest request,ModelMap modelMap,String username,String pwd){
		
		curuser = (UserInfo)request.getSession().getAttribute("iuserinfo");
		
		if(username==null&&curuser==null){
			 //服务器重启后页面非正常情况访问
			 return "/index/login";
		 }
		if(username==null){
			//刷新页面不从登陆页面获取值，可直接从缓存中获取验证
			username = curuser.getUsername();
			pwd =curuser.getPwd();
		}
		
		 UserInfo user =  userService.checkLogin(username,pwd);
		 if(user==null){
			 //该用户不存在
			 modelMap.addAttribute("msg","用户名或密码错误");
			 request.setAttribute("loginerror","1");
			 modelMap.addAttribute("username", username);
			 return "/index/login";
		 }else{
			 //成功登陆系统
			 request.getSession().setAttribute("iuserinfo",user);
			 
			 //获取菜单
			 List<MenuInfo> userMenuList = menuService.findMenuByUserid(user.getId());
			 modelMap.addAttribute("userMenuList", userMenuList);
			 
			 modelMap.addAttribute("iuser", user);
		 }
		
		return "/index/main";
	}
	
	
	
	@RequestMapping("index")
	public String index(ModelMap modelMap){
		
		return "/index/index";
	}
	
	
}
