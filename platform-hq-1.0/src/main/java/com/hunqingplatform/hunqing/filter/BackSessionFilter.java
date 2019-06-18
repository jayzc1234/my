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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.pojo.HqUser;

@WebFilter(urlPatterns = "/auth/back/*",filterName = "BackSessionFilter")
public class BackSessionFilter implements Filter{
	private static Logger logger = LoggerFactory.getLogger(BackSessionFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		//设置支持跨域
		HqUser user=(HqUser) req.getSession().getAttribute("backUser");
		if (null==user) {
			String uri=req.getRequestURI();
			boolean con=uri.contains("/auth/back/file/download");
			logger.info("后台请求地址："+uri+",是否是文件下载路径："+con);
			if (con) {
				user=(HqUser) req.getSession().getAttribute("frontUser");
				if (null!=user) {
					chain.doFilter(req, resp);
					return;
				}
			}
			resp.setHeader("Content-type", "application/json; charset=utf-8"); 
			RestResult<String> rest=new RestResult<String>();
			rest.setMsg("后台用户未登录");
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
