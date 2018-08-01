package com.vue.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.ResourceCodeUtil;
import com.zpj.common.ResultData;
import com.zpj.sys.service.DictionaryService;

import io.swagger.annotations.Api;

@Controller
@RequestMapping("/vue/dictionary")
@Api(value = "/vue/dictionary",tags="字典功能接口", description = "字典接口")
public class DictionaryAppController extends BaseController{
	@Autowired
	public DictionaryService dictionaryService;
	
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
}
