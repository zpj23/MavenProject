package com.zpj.sys.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zpj.common.BaseController;
import com.zpj.common.FileHelper;
import com.zpj.sys.service.UploadfileService;


@Controller
@RequestMapping("file")
public class FileController extends BaseController{

	@Autowired
	private UploadfileService uploadfileService;
	
	@RequestMapping("toFile")
	public String toFile(ModelMap modelMap){
		
		return "/test/file";
	}
	
	
	@RequestMapping(value="doFile")
	public String doFile(MultipartFile file,HttpServletRequest request,
			ModelMap modelMap){
		//上传附件		
		String path = uploadfileService.uploadFile(request,file,null,null);
		
		modelMap.addAttribute("path", path);
		return "/test/file";
	}
	
	
	
	@RequestMapping("upload")
	@ResponseBody
	public void file(HttpServletRequest request,MultipartFile file,String tableid,String modeltype) {				
		jsonData = uploadfileService.uploadFile(request,file,tableid,modeltype);
		this.jsonWrite(jsonData);
	}
	
	@RequestMapping("uploadMultiply")
	@ResponseBody
	public void uploadMultiply(HttpServletRequest request,MultipartFile file,String tableid,String modeltype) {				
		Map map = uploadfileService.uploadMultiply(request,file,tableid,modeltype);
		map.put("code", 0);
		this.jsonWrite2(map);
	}
	
	@RequestMapping("findFiles")
	@ResponseBody
	public void findFiles(String tableid,String modeltype) {				
		String path = uploadfileService.findFiles(tableid,modeltype);
		this.jsonWrite(path);
	}
	/**
	 * 动态表单中多附件上传的后台
	 * @Title findFilesModeltypes
	 * @param tableid
	 * @param modeltype
	 * @author zpj
	 * @time 2018年5月14日 下午2:20:10
	 */
	@RequestMapping("findFilesModeltypes")
	@ResponseBody
	public void findFilesModeltypes(String tableid,String modeltype) {		
		String[] types=modeltype.split(",");
		Map map=new HashMap();
		for(int m=0;m<types.length;m++){
			String path = uploadfileService.findFiles(tableid,types[m]);
			map.put(types[m], path);
		}
		this.jsonWrite2(map);
	}
	
	
	
	@RequestMapping("delFile")
	@ResponseBody
	public void delFile(String fileid,String filepath) {
		try{
			//删除数据
			uploadfileService.delFile(fileid);
			//删除文件
			String path = request.getSession().getServletContext().getRealPath("/");
			FileHelper.delFile(path+filepath);
			this.jsonWrite("0");
		}catch (Exception e) {
			e.printStackTrace();
			this.jsonWrite("1");
		}
	}
	
	
	@RequestMapping("downloadFile")
	@ResponseBody
	public void download(String filepath,String filename) {
		
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path+filepath;
			FileHelper.downloadFile(path, filename, response);			
		
	}
	
	
//	@RequestMapping("/image")  
//	@ResponseBody
//	public void image(@RequestParam("file")MultipartFile file,String path,HttpServletResponse response) throws IOException { 
//		ResultData<Object> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/image.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
//		try {
//			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
//			if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
//				rd.setSuccess(false);
//				rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的图片文件！");
//			}else{ // 10m
//				rd.setMsg(FileUploadUtils.upload(file, path, request));
//				rd.setSuccess(true);
//			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传图片");
//			
//		} catch (Exception e) {
//			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传图片");
//		}
//		response.setCharacterEncoding("UTF-8");
//		//response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//		response.getWriter().write(EmiJsonObj.fromObject(true).toString());
//	} 
//
//	@RequestMapping("/video")  
//	@ResponseBody  
//	public void video(@RequestParam("file")MultipartFile file,String path,HttpServletResponse response) throws IOException { 
//		ResultData<Object> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/video.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
//		try {
//			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
//			if("mp3,wav,wma,mp4".indexOf(suffix)<0){
//				rd.setSuccess(false);
//				rd.setMsg("文件格式不正确，请上传mp3,wav,wma,mp4格式的音频文件！");
//			}else{
//				rd.setMsg(FileUploadUtils.upload(file, path, request));
//				rd.setSuccess(true);
//			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传视频");
//		} catch (Exception e) {
//			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传视频");
//		}
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//	}
//
//	@RequestMapping("/photo")  
//	@ResponseBody  
//	public void photo(@RequestParam("file")MultipartFile file,String path,HttpServletResponse response) throws IOException { 
//		ResultData<Object> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/photo.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
//		try {
//			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
//			if("jpg,png,bmp,jpeg".indexOf(suffix)<0){
//				rd.setSuccess(false);
//				rd.setMsg("文件格式不正确，请上传jpg,png,bmp,jpeg格式的图片文件！");
//			}else{
//				rd.setMsg(FileUploadUtils.upload(file, path, request));
//				rd.setSuccess(true);
//			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传照片");
//		} catch (Exception e) {
//			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传照片");
//		}
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(EmiJsonObj.fromObject(rd).toString());
//	}
//
//	@RequestMapping("/file")  
//	@ResponseBody  
//	public void file(@RequestParam("file")MultipartFile file,String path) { 
//		ResultData<Map> rd = new ResultData<>();
//		String issave=CodeHelper.getCodeValue("issave");
//		String url="/upload/file.do";
//		String parmas="";
//		parmas="file="+file+"&path="+path;
//		try {
//			String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1).toLowerCase();
//			if("mp3,mp4,wav,wma,jpg,png,bmp,jpeg,txt,doc,docx,xls,lrc".indexOf(suffix)<0){
//				rd.setSuccess(false);
//				rd.setMsg("文件格式不正确，请上传mp3,mp4,wav,wma,jpg,png,bmp,jpeg,txt,doc,docx,xls,lrc格式的文件！");
//			}else{
//				Map m = new HashMap();
//				m.put("path", FileUploadUtils.upload2(file, path, request));
//				m.put("name", file.getOriginalFilename());
//				rd.setData(m);
//				rd.setSuccess(true);
//			}
//			if(issave.equals("true"))
//				Logger.getLogger(this.getClass()).info(url+"|"+parmas+"|上传文件");
//		} catch (Exception e) {
//			rd.setSuccess(false);
//			Logger.getLogger(this.getClass()).error(e.getMessage()+"|"+url+"|"+parmas+"|上传文件");
//		}
//		responseJson(rd);
//	}

	
	
}
