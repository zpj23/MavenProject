package socialmanage.com.goldenweb.sys.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import socialmanage.com.goldenweb.sys.common.BaseDao;
import socialmanage.com.goldenweb.sys.dao.LoginDao;
import socialmanage.com.goldenweb.sys.entity.UserInfo;
@Repository
@Transactional
public class LoginDaoImpl extends BaseDao<UserInfo> implements LoginDao{
	
	public void insertUser(UserInfo user){
		this.save(user);
	}
}
