package com.zpj.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.sys.entity.Goods;
import com.zpj.sys.entity.Supplier;
import com.zpj.sys.service.GoodsService;
import com.zpj.sys.service.SupplierService;

@Controller
@RequestMapping("/goodsInfo")
public class GoodsController extends BaseController{
	@Autowired
	public GoodsService goodsService;
	
	@RequestMapping("/toList")
	public String toList(){
		return "sys/goods/list";
	}
	/**
	 * @Description (初始化列表)
	 * @title userList
	 * @param param void
	 * @author zpj
	 * @Date 2018年4月6日 上午10:12:51
	 */
	@RequestMapping("initList")
	@ResponseBody
	public void initList(String param,int page,int limit){
		MyPage pagedata =goodsService.findPageData(param,page,limit);		
		this.jsonWrite2(pagedata);
	}
	
	/**
	 * @Description (跳转供应商添加编辑页面)
	 * @title add
	 * @param id  主键信息用于查询编辑的对象，如果为空则是新增
	 * @author zpj
	 * @Date 2018年4月6日 上午10:13:09
	 */
	@RequestMapping("/toAdd")
	public String add(String id){
		Goods sup=new Goods();
		if(null!=id&&!"".equalsIgnoreCase(id)){
			sup=goodsService.findById(Integer.parseInt(id));
		}else{
			sup=new Goods();
		}
		setRequstAttribute("info", sup);
		return "sys/goods/add";
	}
	/**
	 * @Description (保存供应商信息)
	 * @title doAdd
	 * @param 
	 * @author zpj
	 * @Date 2018年4月6日 上午10:15:34
	 */
	@RequestMapping("doAdd")
	@ResponseBody
	public void doAdd(Goods sup){
		goodsService.saveInfo(sup);
		Map map=new HashMap();
		map.put("flag", true);
		jsonWrite2(map);
	}
	
	@RequestMapping("/doDel")
	@ResponseBody
	public void doDel(String ids){
		goodsService.delete(ids);
		Map map=new HashMap();
		map.put("flag", true);
		jsonWrite2(map);
	}
	
}
