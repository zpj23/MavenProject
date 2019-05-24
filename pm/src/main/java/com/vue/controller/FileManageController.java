package com.vue.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zpj.common.BaseController;
import com.zpj.sys.service.UploadfileService;

import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("vue/file")
@Api(value = "/vue/file",tags="附件功能接口", description = "附件接口")
public class FileManageController extends BaseController{
	@Autowired
	private UploadfileService uploadfileService;
	
	
	@RequestMapping("upload")
	@ResponseBody
	public void uploadMultiply(HttpServletRequest request,MultipartFile file,String tableid,String modeltype) {
		tableid=request.getParameter("uid");
		modeltype=request.getParameter("type");
		MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest)request;
		Iterator<String> fns=mRequest.getFileNames();//获取上传的文件列表
		List<Map> retList=new ArrayList<Map>();
		Map map;
		while(fns.hasNext()){
			String s =fns.next();
			System.out.println(s+"==="+mRequest.getFile(s));
//          System.out.println(mRequest.getFile(s));//get file success!!!!!
			MultipartFile mFile = mRequest.getFile(s);
			map=new HashMap();
			if(mFile.isEmpty()){
				map.put("error", "EventAction.picture.failed");
				map.put("status", 500);
			}else{
				map=uploadfileService.uploadMultiply(request,mFile,tableid,modeltype);
				map.put("status", 200);
			}
			retList.add(map);
		}
		this.jsonWrite2(retList);
	}

}
