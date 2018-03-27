package com.totoro.mobile.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.totoro.core.model.UserInfo;
import com.totoro.core.service.UserService;
import com.totoro.core.utils.MyController;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import io.swagger.annotations.Api;

@Api(value="用户controller",tags={"用户操作接口"})
@RestController
@RequestMapping("/rest/rest2Service")
public class Rest2Service extends MyController{

	
	 @Autowired
	 private UserService userService;
	 
	 @PostMapping(value="/getStr")
	 @ApiOperation(value = "测试内容", httpMethod = "POST",response = String.class, notes = "输出文字",produces = MediaType.TEXT_PLAIN)
	 @ResponseBody
	 public String getStr() {
		 response.setContentType("text/html;charset=utf-8");
		 return "月光狮子";
	 }
	 
	 
	 
	 @PostMapping(value="/getUser")
	 @ApiOperation(value = "测试内容", httpMethod = "POST",tags="获取用户信息", response = String.class, notes = "输出实体",produces = MediaType.APPLICATION_JSON)
	 public UserInfo getUser(@ApiParam( name = "phone", value = "手机号" )    @RequestParam("phone")  String phone) {
		 UserInfo info = new UserInfo();
		 info.setUsername("加洛特");
		 info.setPhone(phone);
		 return info;
	 }
	 
	 @PostMapping(value="/getUserList")
	 @ApiOperation(value = "测试内容", httpMethod = "POST",tags="获取用户信息list", response = String.class, notes = "输出list",produces = MediaType.APPLICATION_JSON)
	 public List<UserInfo> getUserList(@ApiParam( name = "phone", value = "手机号" )    @RequestParam("phone")  String phone) {
		 UserInfo info = new UserInfo();
		 info.setUsername("加洛特");		 
		 info.setPhone(phone);
		 
		 List<UserInfo> list = new ArrayList<UserInfo>();
		 list.add(info);
		 info = new UserInfo();
		 info.setUsername("QQ");
		 info.setPhone(phone);
		 list.add(info);
		 
		 return list;
	 }
	 
	 
	 @PostMapping(value="/test")
	 @ApiOperation(value = "测试内容", httpMethod = "POST",tags="获取用户信息", response = String.class, notes = "输出实体",produces = MediaType.APPLICATION_JSON)
	 public UserInfo test(@ApiParam( name = "phone", value = "手机号" )    @RequestParam("phone")  String phone) {
		 UserInfo info = userService.findUserById("");
		 info.setUsername("加洛特");
		 info.setPhone(phone);
		 return info;
	 }
	
}
