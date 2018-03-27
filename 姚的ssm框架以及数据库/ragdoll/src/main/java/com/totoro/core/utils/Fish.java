package com.totoro.core.utils;

import java.util.List;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Fish {

	public static Boolean StringIsNull(String val){
		if(val==null||"".equals(val)){
			return true;
		}else{
			return false;
		}
	}
	
	public static Boolean StringNotNull(String val){
		if(val!=null&&!"".equals(val)){
			return true;
		}else{
			return false;
		}
	}
	
	public static Boolean ListNotNull(List list){
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	
	public static String  addSingleQuote(String vals,String splitflag){
		String str="";
		String val [] = vals.split(splitflag);
		for(int i=0;i<val.length;i++){
			str+="'"+val[i]+"',";
		}
		str = str.substring(0,str.length()-1);
		return str;
	}
	
	
	public static void jsonWrite(HttpServletResponse response,String json_) throws IOException{		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(json_);
	}
	
	
	public static void jsonWrite2(HttpServletResponse response,Object object) throws IOException{		
		Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		String str = gson.toJson(object);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");	
		response.getWriter().write(str);
	}
	
}
