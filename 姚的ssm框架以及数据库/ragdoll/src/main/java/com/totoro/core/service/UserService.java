package com.totoro.core.service;

import java.util.List;

import com.totoro.core.model.UserInfo;
import com.totoro.core.utils.MyPage;


public interface UserService {

	UserInfo getUserById(String id);
	
	List<UserInfo> getUsers();
	
	int insert(UserInfo userInfo);

	MyPage findUserPage(String selectname, Integer page, Integer pagesize);

	void saveInfo(UserInfo userinfo);

	UserInfo checkLogin(String username, String pwd);

	UserInfo findUserById(String id);
}
