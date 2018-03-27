package com.totoro.core.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.totoro.core.model.UserInfo;

public class MyController {

	    //返回的json变量
	    public String jsonData="";
	    
	    public UserInfo curuser;
	    
	    @Autowired  
		public  HttpServletRequest request;

		@Autowired  
		public  HttpServletResponse response;
	    
		
		public String toJson(Object object){
			 Gson gson = new GsonBuilder()
				.setDateFormat("yyyy-MM-dd HH:mm:ss")
				.create();
			 String str = gson.toJson(object);
			 return str;
		 }
	    
	    public void responseJson(Object o){
			try {
				request.setCharacterEncoding("utf-8");
				response.setContentType("application/json;charset=utf-8");
				
				response.getWriter().write(toJson(o));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
