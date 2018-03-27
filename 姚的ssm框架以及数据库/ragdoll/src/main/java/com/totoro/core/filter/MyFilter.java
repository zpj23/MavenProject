package com.totoro.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MyFilter implements Filter{

	protected String endcoding = null;
	protected FilterConfig filterConfig = null;
	
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) servletRequest;		
		//字符集设置
		servletRequest.setCharacterEncoding(endcoding);
		chain.doFilter(servletRequest, servletResponse);
	}

	
	public void init(FilterConfig config) throws ServletException {
		//spring上下文获取,不可少
		//SpringContext.context = WebApplicationContextUtils.getWebApplicationContext(config.getServletContext());
				
		this.filterConfig=config;
		this.endcoding = filterConfig.getInitParameter("encoding");
	}

}
