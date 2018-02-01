package org.ssm.dufy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.ssm.dufy.entity.User;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:applicationContext.xml"})
public class IUserServiceTest {

	@Autowired
	public IUserService userService;
	
	public void getUserByIdTest(){
	 
		User user = userService.getUserById(1);
		System.out.println(user.getUserName());
	}
	
}
