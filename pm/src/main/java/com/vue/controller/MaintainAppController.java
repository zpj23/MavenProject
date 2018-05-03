package com.vue.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.DateHelper;
import com.zpj.common.MyPage;
import com.zpj.materials.entity.Maintain;
import com.zpj.materials.service.MaintainService;

import io.swagger.annotations.Api;
@Controller
@RequestMapping("/vue/maintain")
@Api(value = "/vue/maintain",tags="维修功能接口", description = "维修接口")
public class MaintainAppController extends BaseController{
	
	@Autowired
	private MaintainService maintainService;
	
	@RequestMapping("/findById")
	@ResponseBody
	public void findById(String id){
		Maintain mt=maintainService.findById(id);
		Map map=new HashMap();
		map.put("msg", true);
		map.put("data", mt);
		jsonWrite2(map);
	}
	
	@RequestMapping("/saveInfo")
	@ResponseBody
	public void saveInfo(String id,String registertime,String username,String jine,String remark ,String loginId,String isAdmin){
		Map map=new HashMap();
		try{
			Maintain mt =new Maintain();
			if(null==id||id.equalsIgnoreCase("")){
				mt.setId(UUID.randomUUID().toString());
			}
			mt.setRegistertime(DateHelper.getDateFromString(registertime, "yyyy-MM-dd"));
			mt.setUsername(username);
			mt.setJine(Double.valueOf(jine));
			mt.setRemark(remark);
			maintainService.saveInfo(mt);
			map.put("msg", true);
//			map.put("data", mt);
		}catch (Exception e) {
			map.put("msg", false);
			e.printStackTrace();
		}
		
		jsonWrite2(map);
	}
	
	@RequestMapping("/findList")
	@ResponseBody
	public void findList(String datemin,String datemax,String username,String ispay,String cpage,String pagerow,String loginId,String isAdmin ){
		Map param=new HashMap();
		param.put("username", username);
		param.put("starttime", datemin);
		param.put("endtime", datemax);
		param.put("ispay", ispay);
		MyPage pagedata =maintainService.findPageData(param,Integer.parseInt(cpage),Integer.parseInt(pagerow));		
		Map map=new HashMap();
		map.put("list", pagedata.getData());
		int tot=(Integer)pagedata.getCount();
		double totalPage=Math.ceil((float)tot/Integer.parseInt(pagerow));
		map.put("totalPage", totalPage);
		this.jsonWrite2(map);
	}
	
	
}
