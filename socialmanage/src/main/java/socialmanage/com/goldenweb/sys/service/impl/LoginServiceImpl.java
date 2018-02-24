package socialmanage.com.goldenweb.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import socialmanage.com.goldenweb.sys.common.BaseService;
import socialmanage.com.goldenweb.sys.dao.LoginDao;
import socialmanage.com.goldenweb.sys.entity.UserInfo;
import socialmanage.com.goldenweb.sys.service.LoginService;

@Service("loginService")
public class LoginServiceImpl extends BaseService<UserInfo> implements LoginService  {
	@Autowired
	public LoginDao loginDao;
	
	@Override
	public void insertUser(UserInfo user) {
//		try {
//			this.save(user);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new RuntimeException();
//		}
		loginDao.insertUser(user);
	}

}
