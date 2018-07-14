package com.vue.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.Goods;
import com.zpj.materials.entity.Supplier;
import com.zpj.materials.service.GoodsService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/vue/goods")
@Api(value = "/vue/goods",tags="商品功能接口", description = "商品接口")
public class GoodsAppController extends BaseController{
	
	
	@Autowired
	public GoodsService goodsService;
	
	@RequestMapping("/findById")
	@ResponseBody
	public void findById(String id){
		Goods mt=goodsService.findById(Integer.parseInt(id));
		Map map=new HashMap();
		map.put("msg", true);
		map.put("data", mt);
		jsonWrite2(map);
	}
	
	@RequestMapping("/findList")
	@ResponseBody
	public void findList(String datemin,String datemax,String username,String cpage,String pagerow,String loginId,String isAdmin ){
		MyPage pagedata =goodsService.findPageData(username,Integer.parseInt(cpage),Integer.parseInt(pagerow));		
		Map map=new HashMap();
		if(null==pagedata.getData()){
			map.put("list", new ArrayList());
		}else{
			map.put("list", pagedata.getData());
		}
		int tot=(Integer)pagedata.getCount();
		double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
		map.put("totalPage", totalPage);
		this.jsonWrite2(map);
	}
	
}
