package com.vue.controller;

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
import com.zpj.common.ResultData;
import com.zpj.materials.service.GoodsService;
import com.zpj.sys.entity.DictionaryType;
import com.zpj.sys.service.DictionaryService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/vue/dictionary")
@Api(value = "/vue/dictionary",tags="字典功能接口", description = "字典接口")
public class DictionaryAppController extends BaseController{
	@Autowired
	public DictionaryService dictionaryService;
	@Autowired
	public GoodsService goodsService;
	
	@RequestMapping("/initThreeType")
	@ResponseBody
	public void initGoodsType(){
		Map temp=new HashMap();
		temp.put("first", ResourceCodeUtil.first);
		temp.put("second", ResourceCodeUtil.second);
		temp.put("third", ResourceCodeUtil.third);
		ResultData<Map> rd=new ResultData<Map>(temp,"查询成功", true);
		this.jsonWrite2(rd);
	}
	@RequestMapping("/getThirdType")
	@ResponseBody
	public void getThirdType(){
		Map map=new HashMap();
		map.put("third", ResourceCodeUtil.third);
		Map param=new HashMap();
		MyPage pagedata =goodsService.findPageData(param,1,500);		
		if(null==pagedata.getData()){
			map.put("goodslist", new ArrayList());
		}else{
			map.put("goodslist", pagedata.getData());
		}
		int tot=(Integer)pagedata.getCount();
		ResultData<Map> rd=new ResultData<Map>(map,"查询成功", true);
		this.jsonWrite2(rd);
	}
	
	
	@RequestMapping("/saveInfo")
	@ResponseBody
	public void saveInfo(String pCode,String pName,String code,String name,String bCode){
		DictionaryType dt= dictionaryService.findDicTypeByCode(bCode);
		if(null!=dt){
			dt.setOrderNum(1);
			dt.setTypeCode(code);
			dt.setTypeName(name);
			DictionaryType temp=dictionaryService.findDicTypeByCode(pCode);
			dt.setParentTypeid(temp.getId());
			dt.setParentTypeName(temp.getTypeName());
			dictionaryService.saveDictionaryTypeByPhone(dt,bCode);
		}else{
			DictionaryType cdt=new DictionaryType();
			cdt.setOrderNum(1);
			cdt.setTypeCode(code);
			cdt.setTypeName(name);
			DictionaryType temp=dictionaryService.findDicTypeByCode(pCode);
			cdt.setParentTypeid(temp.getId());
			cdt.setParentTypeName(temp.getTypeName());
			dictionaryService.saveDictionaryTypeByPhone(cdt,bCode);
		}
		
		ResultData<Map> rd=new ResultData<Map>(null,"保存成功", true);
		this.jsonWrite2(rd);
	}
	
	
	@RequestMapping("/delByCode")
	@ResponseBody
	public void delByCode(String code){
		DictionaryType dt=	dictionaryService.findDicTypeByCode(code);
		if(null!=dt){
			dictionaryService.delDictionaryType(String.valueOf(dt.getId()));
		}
		ResultData<Map> rd=new ResultData<Map>(null,"删除成功", true);
		this.jsonWrite2(rd);
	}
	
}
