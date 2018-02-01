package com.xrom.ssh.comm;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;


/**
 * @ClassName: SpringContext
 * @Description: TODO(Spring上下文)
 * @author Lee
 * @date 2014-2-10 下午3:40:16
 */
@Component
public class SpringContext implements ApplicationContextAware{

    public static ApplicationContext context;
        
    private static Logger logger = LoggerFactory.getLogger(SpringContext.class);
    
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        logger.debug("注入ApplicationContext到SpringContext:" + applicationContext);
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

    public static <T> T getBean(String name){
    	assertContextInjected();
		return (T) context.getBean(name);
    }
    
	public static <T> T getBean(Class<T> requiredType) {
		assertContextInjected();
		return context.getBean(requiredType);
	}
    
	private static void assertContextInjected() {
		if (context == null) {
			throw new IllegalStateException(
					"applicaitonContext未注入,请在applicationContext.xml中定义SpringContext");
		}
	}
	
	
	
	/**
	 * 国际化语设置
	 */
	public static LocaleResolver localeResolver = null;

//	public static MessageSource messageSource = null;
	
	
	/**
	 * java类中获取资源文件
	 * 
	 * @param request
	 * @param messageCode
	 * @return
	 */
//	public static String getMessage(HttpServletRequest request,
//			String messageCode) {
//		String message = SpringContext.messageSource.getMessage(messageCode,
//				null, "", SpringContext.localeResolver.resolveLocale(request));
//		return message;
//	}
	
	
	/**
	 * 自动化任务
	 */
//	public static SchedulerFactory scheduler = null;
    
}