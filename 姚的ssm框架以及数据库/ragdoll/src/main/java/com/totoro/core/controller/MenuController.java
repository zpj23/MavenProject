package com.totoro.core.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.totoro.core.model.MenuInfo;
import com.totoro.core.service.MenuService;
import com.totoro.core.utils.Fish;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	
	
	@RequestMapping("/showChildMenu")
	@ResponseBody
	public  void showChildMenu(HttpServletResponse response,String pid) throws IOException{
	    String  menuJson = menuService.findMenuJsonByParentid(pid);
		Fish.jsonWrite(response, menuJson);
	}
	
}
