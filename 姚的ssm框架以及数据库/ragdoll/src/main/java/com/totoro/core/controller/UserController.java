package com.totoro.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.totoro.core.model.UserInfo;
import com.totoro.core.service.UserService;
import com.totoro.core.utils.Fish;
import com.totoro.core.utils.MyController;
import com.totoro.core.utils.MyPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController extends MyController{

	@Autowired
	private UserService userService;
	
	private UserInfo userinfo;
	
	
	@RequestMapping("toList")
	public String toList(ModelMap modelMap){
		
		return "/user/toList";
	}
	
	@RequestMapping("/showInfo/{userId}")
	public String showUserInfo(ModelMap modelMap, @PathVariable String userId){
		UserInfo userInfo = userService.getUserById(userId);
		modelMap.addAttribute("userInfo", userInfo);
		return "/user/showInfo";
	}
	
	@RequestMapping("/userData")
	@ResponseBody
	public  void showUserInfos(HttpServletResponse response,String selectname,Integer page,Integer rows) throws IOException{
		if(page==null){
			page =1;
		}
		MyPage pagedata = userService.findUserPage(selectname,page,rows);
		Fish.jsonWrite2(response, pagedata);
	}
	
	
	@RequestMapping("toAdd")
	public String toAdd(ModelMap modelMap,String id){
		if(Fish.StringNotNull(id)){
		userinfo = userService.getUserById(id);
		modelMap.addAttribute("userinfo", userinfo);
		}
		return "/user/add";
	}
	
	
	@RequestMapping("/doAdd")
	@ResponseBody
	public void doAdd(ModelMap modelMap,UserInfo userinfo){
		try {
			userService.saveInfo(userinfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
