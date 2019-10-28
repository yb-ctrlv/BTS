package com.kh.bts.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter{

	Logger logger = LoggerFactory.getLogger(LogFilter.class);

	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		req.setCharacterEncoding("UTF-8");
		
		logger.info("요청URI : " + req.getRequestURI());
		chain.doFilter(req, response);
	}


}
