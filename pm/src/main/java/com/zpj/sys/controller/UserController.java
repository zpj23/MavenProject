package com.zpj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zpj.common.BaseController;
import com.zpj.common.MyPage;
import com.zpj.sys.service.UserService;

/**
 * @ClassName UserController
 * @Description TODO(用户管理后台)
 * @author zpj
 * @Date 2018年4月6日 上午10:12:28
 * @version 1.0.0
 */
@Controller
@RequestMapping("userinfo")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("toList")
	public String toUserList(){
		return "";
	}
	
	/**
	 * @Description (用户列表)
	 * @title userList
	 * @param param void
	 * @author zpj
	 * @Date 2018年4月6日 上午10:12:51
	 */
	@RequestMapping("initList")
	@ResponseBody
	public void userList(String param){
		MyPage pagedata =userService.findPageData(param,page,limit);		
		this.jsonWrite2(pagedata);
	}
	
	/**
	 * @Description (跳转用户添加编辑页面)
	 * @title addUser
	 * @param id  主键信息用于查询编辑的对象，如果为空则是新增
	 * @author zpj
	 * @Date 2018年4月6日 上午10:13:09
	 */
	@RequestMapping("toAdd")
	public String addUser(String id){
		return "";
	}
	/**
	 * @Description (保存用户信息)
	 * @title doAddUser
	 * @param user void
	 * @author zpj
	 * @Date 2018年4月6日 上午10:15:34
	 */
	@RequestMapping("doAdd")
	@ResponseBody
	public void doAddUser(String user){
		
	}
	
	
	
}
