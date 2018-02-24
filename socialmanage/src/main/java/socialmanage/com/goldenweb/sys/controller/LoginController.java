package socialmanage.com.goldenweb.sys.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import socialmanage.com.goldenweb.sys.entity.UserInfo;
import socialmanage.com.goldenweb.sys.service.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	public LoginService loginService;
	
	
	 @RequestMapping("/testPage")
     public String controllerTest(ModelMap map){
        String runTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        map.addAttribute("date", runTime);
        System.out.println("进入controller里,当前时间："+runTime);
        UserInfo user=new UserInfo();
        user.setId(1);
        user.setName("测试插入数据库");
        loginService.insertUser(user);
        return "home/index";
     }
}
