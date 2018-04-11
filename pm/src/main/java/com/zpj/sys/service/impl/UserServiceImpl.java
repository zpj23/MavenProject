package com.zpj.sys.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zpj.common.BaseDao;
import com.zpj.common.MyPage;
import com.zpj.sys.entity.User;
import com.zpj.sys.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BaseDao<User> userDao;

	@Override
	public MyPage findPageData(String username, Integer page, Integer limit) {
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("name-like", username);
		Map px=new HashMap();
//		JSONArray json = JSONArray.fromObject(sort); // [{"property":"FL_TableID","direction":"ASC"}]
//		if(json.size()>0){
//		  for(int i=0;i<json.size();i++){
//		    JSONObject job = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
//		    px.put("FL_TableID+Fl_Hypocorism", "ASC");
//		  }
//		}
		
		return userDao.findPageDateSqlT("sys_userinfo", param,px , page, limit, User.class);
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
