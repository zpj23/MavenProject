package com.zpj.sys.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.sys.entity.DictionaryType;
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
	 * 跳转item列表页面
	 * @Title toListItem
	 * @return
	 * @author zpj
	 * @time 2018年7月4日 下午4:36:43
	 */
	@RequestMapping("/toListItem")
	public String toListItem(){
		return "sys/dictionary/list_item";
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
	/**
	 * 跳转字典类型编辑页面
	 * @Title toSave
	 * @param pid
	 * @param cid
	 * @return
	 * @author zpj
	 * @time 2018年7月4日 下午4:36:13
	 */
	@RequestMapping("/toSave")
	public String toSave(String pid,String cid){
		DictionaryType dt=	dictionaryService.findDicTypeId(pid);
		DictionaryType info=dictionaryService.findDicTypeId(cid);
//		DictionaryType pdt=dictionaryService.findDicTypeId(String.valueOf(dt.getParentTypeid()));
		
		if(null==info){
			info=new DictionaryType();
		}
		info.setParentTypeName(dt.getTypeName());
		info.setParentTypeid(dt.getId()); 
		request.setAttribute("dt", info);
		return "sys/dictionary/save";
	}
	/**
	 * 保存字典类型
	 * @Title doAddDictionaryType
	 * @param dt
	 * @author zpj
	 * @time 2018年7月4日 下午4:36:02
	 */
	@RequestMapping("/doAddDictionaryType")
	@ResponseBody
	public void doAddDictionaryType(DictionaryType dt){
		dictionaryService.saveDictionaryType(dt);
		Map map=new HashMap();
		map.put("flag", true);
		jsonWrite2(map);
	}
	/**
	 * 删除字典类型
	 * @Title doDelDictionaryType
	 * @param id
	 * @author zpj
	 * @time 2018年7月4日 下午4:35:52
	 */
	@RequestMapping("/doDelDictionaryType")
	@ResponseBody
	public void doDelDictionaryType(String id){
		dictionaryService.delDictionaryType(id);
		Map map=new HashMap();
		map.put("flag", true);
		jsonWrite2(map);
	}
	
}
