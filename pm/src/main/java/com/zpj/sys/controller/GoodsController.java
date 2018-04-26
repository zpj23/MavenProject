package com.zpj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;

@Controller
@RequestMapping("/goodsInfo")
public class GoodsController extends BaseController{
	
	@RequestMapping("/toList")
	public String toList(){
		return "sys/goods/list";
	}
	
	@RequestMapping("initList")
	@ResponseBody
	public void initList(String param,int page,int limit){
//		MyPage pagedata =userService.findPageData(param,page,limit);		
//		this.jsonWrite2(pagedata);
	}
	
}
