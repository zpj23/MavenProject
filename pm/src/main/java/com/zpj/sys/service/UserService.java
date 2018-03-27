package com.zpj.sys.service;

import com.zpj.common.MyPage;
import com.zpj.sys.entity.User;

public interface UserService {
	MyPage findPageData(String username, Integer page, Integer limit);
	
	void saveInfo(User info);

	void deleteUser(String deleteID);
	
	public User findById(String id);
}
