package com.zpj.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.common.ResourceCodeUtil;
import com.zpj.sys.entity.DictionaryItem;
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
	public String toListItem(String id){
		DictionaryType dt= dictionaryService.findDicTypeId(id);
		if(dt!=null){
			request.setAttribute("typeCode",dt.getTypeCode());
			request.setAttribute("typeName", dt.getTypeName());
		}
		return "sys/dictionary/list_item";
	}
	@RequestMapping("/initItemList")
	@ResponseBody
	public void initItemList(String typeCode,int page,int limit){
		MyPage pagedata =dictionaryService.findPageData(typeCode,page,limit);		
		this.jsonWrite2(pagedata);
	}
	@RequestMapping("/saveItem")
	@ResponseBody
	public void saveItem(DictionaryItem di){
		dictionaryService.saveItem(di);
		Map map=new HashMap();
		map.put("flag", true);
		jsonWrite2(map);
	}
	@RequestMapping("/toAddItem")
	public String toAddItem(String id,String typeCode){
		DictionaryItem di;
		if(null!=id&&!id.equalsIgnoreCase("")){
			di=dictionaryService.findItem(id);
		}else{
			di=new DictionaryItem();
			di.setTypeCode(typeCode);
		}
		request.setAttribute("info", di);
		return "sys/dictionary/add_item";
	}
	@RequestMapping("/doDelItem")
	@ResponseBody
	public void doDelItem(String ids){
		dictionaryService.delItem(ids);
		Map map=new HashMap();
		map.put("flag", true);
		jsonWrite2(map);
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
	@RequestMapping("/updateDic")
	@ResponseBody
	public void updateDic(){
		System.out.println("----初始化字典数据-----");
		ResourceCodeUtil.itemMap.clear();
		ResourceCodeUtil.typeMap.clear();
		ResourceCodeUtil.dpsp.clear();
		ResourceCodeUtil.dpspType.clear();
		ResourceCodeUtil.first.clear();
		ResourceCodeUtil.second.clear();
		ResourceCodeUtil.third.clear();
		/********初始化所有type信息**开始*********/
		List<DictionaryType> typeList=dictionaryService.findAllDictionaryType();
		List<DictionaryType> topList=new ArrayList<DictionaryType>();
		if(null==typeList||typeList.size()==0){
			return ;
		}
		for(int i=0;i<typeList.size();i++){
			if(typeList.get(i).getParentTypeid()==1){
				topList.add(typeList.get(i));
				ResourceCodeUtil.typeMap.put(typeList.get(i).getTypeCode(),ResourceCodeUtil.findSon(typeList.get(i),typeList));
			}
		}
		ResourceCodeUtil.typeMap.put("ROOT", topList);
		
		/********初始化所有type信息**结束*********/
		
		
		/********初始化商品分类信息**开始*********/
		ResourceCodeUtil.initGoodsType("");
		for(int q=0;q<typeList.size();q++){
			ResourceCodeUtil.dpspType.put(typeList.get(q).getTypeCode(),typeList.get(q).getTypeName());
		}
		/********初始化商品分类信息**结束*********/
		
		/********初始化字典值信息**开始**************************/
		DictionaryType dt;
		for(int j=0;j<typeList.size();j++){
			dt=typeList.get(j);
			ResourceCodeUtil.itemMap.put(dt.getTypeCode(), new ArrayList<DictionaryItem>());
		}
		List<DictionaryItem> itemList=dictionaryService.findAllDictionaryItem();
		List<DictionaryItem> temp;
		if(null!=itemList&&itemList.size()>0){
			for(int m=0;m<itemList.size();m++){
				temp=ResourceCodeUtil.itemMap.get(itemList.get(m).getTypeCode());
				temp.add(itemList.get(m));
				ResourceCodeUtil.itemMap.put(itemList.get(m).getTypeCode(), temp);
			}
		}
		/********初始化字典值信息**结束**************************/
	}
	
}
