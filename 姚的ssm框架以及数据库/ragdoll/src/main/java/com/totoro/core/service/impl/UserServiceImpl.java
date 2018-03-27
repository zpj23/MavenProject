package com.totoro.core.service.impl;

import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totoro.core.dao.UserInfoMapper;
import com.totoro.core.model.UserInfo;
import com.totoro.core.model.UserInfoExample;
import com.totoro.core.service.UserService;
import com.totoro.core.utils.Fish;
import com.totoro.core.utils.MyPage;


@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserInfoMapper userInfoMapper;

	
	public UserInfo getUserById(String id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}

	
	public List<UserInfo> getUsers() {
		UserInfoExample example = new UserInfoExample();
		example.setOrderByClause("createtime");
		return userInfoMapper.selectByExample(example);
	}

	
	public int insert(UserInfo userInfo) {
		
		int result = userInfoMapper.insert(userInfo);
		
		System.out.println(result);
		return result;
	}


	
	public MyPage findUserPage(String selectname, Integer page, Integer pagesize) {
		page = (page-1)*pagesize;
		List<UserInfo> list = userInfoMapper.selectUserPage(selectname,page,pagesize);
		
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria c = example.createCriteria();
		c.andUsernameLike("%"+selectname+"%");		
		int total = userInfoMapper.countByExample(example);
		
		return new MyPage(list, total);
	}


	
	public void saveInfo(UserInfo userinfo) {
        if("".equals(userinfo.getId())){
        	userinfo.setId(Fish.getUUID());
        	userinfo.setCreatetime(new Date());
        	userInfoMapper.insert(userinfo);
        }else{
        	userInfoMapper.updateByPrimaryKeySelective(userinfo);
        }		
	}


	
	public UserInfo checkLogin(String username, String pwd) {
		UserInfoExample example = new UserInfoExample();
		UserInfoExample.Criteria c = example.createCriteria();
		c.andUsernameEqualTo(username);
		c.andPwdEqualTo(pwd);
		 List<UserInfo> list = userInfoMapper.selectByExample(example);
		 if(Fish.ListNotNull(list)){
			 return list.get(0);
		 }		 
		 return null;
	}


	@Override
	public UserInfo findUserById(String id) {
		return userInfoMapper.selectByPrimaryKey(id);
	}


	

}
