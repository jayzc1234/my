package com.hunqingplatform.hunqing.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.pojo.HqUser;

@WebFilter(urlPatterns = "/auth/front/*",filterName = "FrontSessionFilter")
public class FrontSessionFilter implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		//设置支持跨域
		HqUser user=(HqUser) req.getSession().getAttribute("frontUser");
		if (null==user) {
			resp.setHeader("Content-type", "application/json; charset=utf-8"); 
			RestResult<String> rest=new RestResult<String>();
			rest.setMsg("前台用户未登录");
			rest.setState(401);
			resp.getOutputStream().write(JSONObject.toJSONString(rest).getBytes());
			resp.getOutputStream().flush();
			return;
		}
		chain.doFilter(req, resp);
	}

	@Override
	public void destroy() {
		
	}

}
