package com.zpj.sys.service.impl;

import java.util.HashMap;
import java.util.List;
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
		    px.put("createtime", "desc");
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
		String[] ids=deleteID.split(",");
		StringBuffer sb=new StringBuffer(500);
		for (int m=0;m<ids.length;m++) {
			if(m>0){
				sb.append(",");
			}
			sb.append("'"+ids[m]+"'");
		}
		userDao.executeSql(" delete from sys_userinfo where id in ("+sb+")");
	}

	@Override
	public User findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}	
	
	
	public User checkLogin(String username,String password){
		
		StringBuffer sql=new StringBuffer("select * from sys_userinfo where loginName='"+username+"' and password='"+password+"'");
		List<User> userlist=userDao.findBySqlT(sql.toString(), User.class);
		if(null!=userlist&&userlist.size()>0){
			return userlist.get(0);
		}
		return null;
		
	}
	
	
}
