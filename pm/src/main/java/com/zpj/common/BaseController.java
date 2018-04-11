package com.zpj.common;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {
	//返回的json变量
    public String jsonData="";
    
    @Autowired  
	public  HttpServletRequest request;

	@Autowired(required=false)
	public  HttpServletResponse response;
	
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public Integer page = 1; // 当前页数
	public Integer limit = 10; // 行数
	

	public Integer getPage() {
		return page;
	}




	public void setPage(Integer page) {
		this.page = page;
	}




	public Integer getLimit() {
		return limit;
	}




	public void setLimit(Integer limit) {
		this.limit = limit;
	}




	public HttpSession getSession() {
		return request.getSession();
	}

	
    
    
	/**
	 * springmvc 对表单的日期类型的特殊处理
	 * @param binder
	 */
    @InitBinder    
    public void initBinder(WebDataBinder binder) {    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
            dateFormat.setLenient(false);    
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
    }
    /*
	 * 获取request参数
	 */
	public String getParameter(String key){
		return request.getParameter(key);
	}
	public Integer getParameterInt(String key){
		return Integer.parseInt(request.getParameter(key));
	}

	/*
	 * 设置request参数
	 */
	public void setRequstAttribute(String key,Object value){
		request.setAttribute(key, value);
	}
	


//	public HashMap<String, String> getRequestMap() {  
//        HashMap<String, String> conditions = new HashMap<String, String>();  
//        Map map = request.getParameterMap();  
//        for (Object o : map.keySet()) {  
//            String key = (String) o;  
//            if(key.equalsIgnoreCase("page")){
//            	String val=((String[]) map.get(key))[0];
//            	if(val!=null&&!"".equals(val)){
//        			page=Integer.parseInt(val);
//        		}
//            	conditions.put(key, page+"");
//            }if(key.equalsIgnoreCase("limit")){
//            	String val=((String[]) map.get(key))[0];
//            	if(val!=null&&!"".equals(val)){
//            		limit=Integer.parseInt(val);
//        		}
//            	conditions.put(key, limit+"");
//            }else{
//            	conditions.put(key, ((String[]) map.get(key))[0]);  
//            }
//        }  
//        return conditions;  
//    }  
	
	/**
	 * 类转json
	 * @param object
	 * @return
	 */
	public String tojson(Object object){	
		Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
		String str = gson.toJson(object);
		return str;
	}
	/**
	 * 输出字符
	 * @param str
	 */
	public void write(String str){	
		try{
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");	
		response.getWriter().write(str);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 返回成功
	 */
	public void writetrue(){
			write("{success:true}");
	}
	/**
	 * 返回失败
	 */
	public void writefalse(){
			write("{success:false}");
	}
	
	/**
	 * 传入json格式字符
	 * @param str
	 */
	public void jsonWrite(String str){		
		try{
			response.setCharacterEncoding("utf-8");
			response.setContentType("json/application;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
			response.getWriter().write(str);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 传入list，实体等
	 * @param object
	 */
	public void jsonWrite2(Object object){		
		try{
			Gson gson =  new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); 
			String str = gson.toJson(object);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=UTF-8");	
			response.setHeader("Access-Control-Allow-Headers", "Cache-Control, Pragma, Origin, Authorization, Content-Type, X-Requested-With");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
			response.getWriter().write(str);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
    
}
