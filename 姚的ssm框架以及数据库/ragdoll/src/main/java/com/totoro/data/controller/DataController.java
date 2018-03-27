package com.totoro.data.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totoro.core.utils.Fish;
import com.totoro.core.utils.MyPage;
import com.totoro.data.model.Node;
import com.totoro.data.service.DataService;

@Controller
@RequestMapping("data")
public class DataController {

	@Autowired
	private DataService dataService; 
	
	@RequestMapping("toLhbList")
	public String toList(ModelMap modelMap){
		System.out.println("------in -----");
		return "/data/toLhbList";
	}
	
	/**
	 * 数据源
	 * @param response
	 * @param selectname
	 * @param page
	 * @param rows
	 * @throws IOException
	 */
	@RequestMapping("/lhbDataJson")
	@ResponseBody
	public  void lhbDataJson(HttpServletResponse response,String selectname,Integer page,Integer rows) throws IOException{
		if(page==null){
			page =1;
		}
		System.out.println("------------------------"+selectname);
		MyPage pagedata = dataService.findDataPage(selectname,page,rows);
		Fish.jsonWrite2(response, pagedata);
	}
	
	
	/**
	 * 抓取龙虎榜的数据
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("gainLhbData")
	@ResponseBody
	public void gainLhbData(HttpServletResponse response) throws IOException{
		try{
		    dataService.gainLhbData();
			Fish.jsonWrite(response, "0");
		}catch (Exception e) {
			e.printStackTrace();
			Fish.jsonWrite(response, "1");
		}
	}
	
	
	@RequestMapping("orgJson")
    @ResponseBody
    public void buildJsonTree(HttpServletResponse response) throws IOException {

        // 获取全部目录节点
        List<Node> nodes = dataService.getAllDirList();

        // 拼装树形json字符串
        String json = new TreeBuilder().buildTree(nodes);
        System.out.println(json);
         Fish.jsonWrite(response, json);
    }
	
}
