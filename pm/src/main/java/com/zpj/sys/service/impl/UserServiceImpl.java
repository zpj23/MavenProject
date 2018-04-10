package com.zpj.sys.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.sys.entity.User;
import com.zpj.sys.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BaseDao<User> userDao;

	@Override
	public MyPage findPageData(String username, Integer page, Integer limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveInfo(User info) {
		if(null!=info.getId()&&!"".equalsIgnoreCase("")){
			User user=this.findById(info.getId());
			if(null!=user){
				userDao.merge(info, info.getId());
			}else{
				userDao.add(info);
			}
		}else{
			info.setId(UUID.randomUUID().toString());
			userDao.add(info);
		}
		
	}

	@Override
	public void deleteUser(String deleteID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	
}
