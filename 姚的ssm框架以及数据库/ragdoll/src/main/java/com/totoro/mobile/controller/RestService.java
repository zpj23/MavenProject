package com.totoro.mobile.controller;

import java.util.Date;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totoro.core.model.UserInfo;
import com.totoro.core.utils.MyController;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Path("/restService")
@Component
@Singleton
@Controller
@RequestMapping("/rest/restService")
@Api(value="/rest/restService",description="接口类文档")
public class RestService extends MyController{
	
	
	
	
	
	 @GET
	 @Path("/getUserText")
	 @ResponseBody
	 @Produces(MediaType.TEXT_PLAIN)
	 @RequestMapping(value="/getUserText")
	 @ApiOperation(value = "测试内容", httpMethod = "GET", response = String.class, notes = "输出文字")
	 public String getUserText() {
	  return "Hello,World!";
	 }
	 
	 
	 @POST
	 @Path("/getS")
	 @ResponseBody
	 @RequestMapping(value="/getS")
	 @ApiOperation(value = "测试内容", httpMethod = "POST", response = String.class, notes = "输出文字",produces = MediaType.TEXT_PLAIN)
	 public String getS() {
	     
		 return "hehe";
	 } 
	 
	 
	 
	 @POST
	 @Path("/getUser")	 
	 @RequestMapping(value="/getUser")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	 @ApiOperation(value = "人员信息", httpMethod = "POST", response = String.class,  notes = "输出文字",produces = MediaType.APPLICATION_JSON)
	 @ResponseBody
	 public void getUser(@Context HttpServletResponse response,@ApiParam(required = true, name = "username", value = "姓名" )  @RequestParam("username") String username,
			 @ApiParam( name = "phone", value = "手机号" )  @RequestParam("phone") String phone ) {		
		 UserInfo info = new UserInfo();
		 info.setUsername(username);
		 info.setPhone(phone);
		 info.setCreatetime(new Date());		 
		 responseJson(info);
	 }

}
