package socialmanage.com.goldenweb.sys.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class BaseController {
	//返回的json变量
    public String jsonData="";
    
    @Autowired  
	public  HttpServletRequest request;

	@Autowired(required=false)
	public  HttpServletResponse response;
	
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	

	public HttpSession getSession() {
		return request.getSession();
	}

	public HttpServletRequest getRequest(){
		return request;
	}

	public HttpServletResponse getResponse(){
		return response;
	}
    
    
	/**
	 * springmvc 对表单的日期类型的特殊处理
	 * @param binder
	 */
    @InitBinder    
    public void initBinder(WebDataBinder binder) {    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
            dateFormat.setLenient(false);    
            binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
    }
    
    
}
