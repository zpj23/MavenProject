package com.zpj.sys.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.sys.service.DictionaryService;


@Controller
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController{
	@Autowired
	public DictionaryService dictionaryService;
	
	@RequestMapping("/toList")
	public String toList(){
		return "sys/dictionary/list";
	}
	/**
	 * 跳转类型tree页面
	 * @Title toTree
	 * @return
	 * @author zpj
	 * @time 2018年4月26日 下午2:53:01
	 */
	@RequestMapping("/toTree")
	public String toTree(){
		return "sys/dictionary/tree";
	}
	
	/**
	 * 初始化树，同时可以根据id和name异步加载树
	 * @Title initTree
	 * @param id
	 * @param name
	 * @author zpj
	 * @time 2018年4月26日 下午3:01:23
	 */
	@RequestMapping("/initTree")
	@ResponseBody
	public void initTree(String id){
		Map<String,String> param=new HashMap<String,String>();
		param.put("id", id);
		if(id!=null&&!id.equalsIgnoreCase("")){
			this.jsonData = dictionaryService.findJson(param);
		}else{
			this.jsonData=dictionaryService.findTopJson();
		}
		this.jsonWrite(jsonData);
	}
}
