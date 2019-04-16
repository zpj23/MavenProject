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
		Map map=new HashMap();
		MultipartHttpServletRequest mRequest=(MultipartHttpServletRequest)request;
		Iterator<String> fns=mRequest.getFileNames();//获取上传的文件列表
		while(fns.hasNext()){
			String s =fns.next();
			System.out.println(s+"==="+mRequest.getFile(s));
//          System.out.println(mRequest.getFile(s));//get file success!!!!!
			MultipartFile mFile = mRequest.getFile(s);
			if(mFile.isEmpty()){
				map.put("error", "EventAction.picture.failed");
			}else{
				uploadfileService.uploadMultiply(request,mFile,tableid,modeltype);
			}
		}

		map.put("code", 0);
		this.jsonWrite2(map);
	}

}
