package com.zpj.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SetCharacterFilter implements Filter{

	protected String endcoding = null;
	protected FilterConfig filterConfig = null;
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		req.setCharacterEncoding(endcoding);		
		chain.doFilter(request, response);
	}
	
	
    public void init(FilterConfig config) throws ServletException {
    	ApplicationContext ac = WebApplicationContextUtils
    			.getWebApplicationContext(config.getServletContext());
    	this.filterConfig=config;
		this.endcoding = filterConfig.getInitParameter("encoding");
	}
	
	public void destroy() {
		this.endcoding = null;
		this.filterConfig = null;
	}
	
}
