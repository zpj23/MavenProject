package com.zpj.sys.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadfileService {

	String uploadFile(HttpServletRequest request, MultipartFile file,String tableid,String modeltype);

	String findFiles(String tableid, String modeltype);

	void delFile(String fileid);

}
