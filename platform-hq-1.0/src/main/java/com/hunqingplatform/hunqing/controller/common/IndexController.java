package com.hunqingplatform.hunqing.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.cache.GlobalRamCache;
import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqUser;
import com.hunqingplatform.hunqing.service.HqUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/unauth/index")
@Api(tags = "登录/重置密码接口")
public class IndexController extends CommonUtil {

	@Autowired
	private HqUserService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ApiOperation(value = "前台登录", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", paramType = "query", defaultValue = "13925121452", value = "手机号", required = true),
			@ApiImplicitParam(name = "password", paramType = "query", defaultValue = "64b379cd47c843458378f479a115c322", value = "密码", required = true) })
	public RestResult<HqUser> frontLogin(@RequestParam String phone, @RequestParam String password,
			HttpServletRequest req) throws Exception {
		return login(phone, password,true, req);
	}
	
	
	@RequestMapping(value = "/backLogin", method = RequestMethod.GET)
	@ApiOperation(value = "后台登录", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", paramType = "query", defaultValue = "13925121452", value = "手机号", required = true),
			@ApiImplicitParam(name = "password", paramType = "query", defaultValue = "64b379cd47c843458378f479a115c322", value = "密码", required = true) })
	public RestResult<HqUser> backLogin(@RequestParam String phone, @RequestParam String password,
			HttpServletRequest req) throws Exception {
		return login(phone, password,false, req);
	}


	private RestResult<HqUser> login(String phone, String password, boolean formFront,HttpServletRequest req) throws CommonException {
		RestResult<HqUser> restResult = new RestResult<HqUser>();
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(password)) {
			restResult.setState(500);
			restResult.setMsg("参数不能为空");
			return restResult;
		}
		HqUser user = service.selectByPhoneAndPassword(phone, password);
		if (null == user) {
			restResult.setState(500);
			restResult.setMsg("账号或者密码错误");
		} else {

			Integer useStatus = user.getUseStatus();
			if (null != useStatus && useStatus.intValue() == 0) {
				restResult.setState(500);
				restResult.setMsg("该用户已禁用");
				return restResult;
			}
			if (formFront) {
				req.getSession().setAttribute("frontUser", user);
			}else {
				req.getSession().setAttribute("backUser", user);
			}

			HqUser frontUser = new HqUser();
			frontUser.setDescText(user.getDescText());
			frontUser.setId(user.getId());
			frontUser.setPhone(user.getPhone());
			frontUser.setRoleId(user.getRoleId());
			frontUser.setUserName(user.getUserName());
			frontUser.setUseStatus(user.getUseStatus());
			restResult.setResults(frontUser);
			restResult.setState(200);
			restResult.setMsg("成功");
		}
		return restResult;
	}
	@RequestMapping(value = "/getBackLoginUserInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取后台登录用户信息", notes = "")
	public RestResult<HqUser> getLoginUserInfo(
			HttpServletRequest req) throws Exception {
		RestResult<HqUser> restResult = new RestResult<HqUser>();
		HqUser user=(HqUser) req.getSession().getAttribute("backUser");
		restResult.setResults(user);
		restResult.setState(200);
		return restResult;
	}

	
	@RequestMapping(value = "/getFrontLoginUserInfo", method = RequestMethod.GET)
	@ApiOperation(value = "获取前台登录用户信息", notes = "")
	public RestResult<HqUser> getFrontLoginUserInfo(
			HttpServletRequest req) throws Exception {
		RestResult<HqUser> restResult = new RestResult<HqUser>();
		HqUser user=(HqUser) req.getSession().getAttribute("frontUser");
		restResult.setResults(user);
		restResult.setState(200);
		return restResult;
	}
	
	@RequestMapping(value = "/backLogout", method = RequestMethod.GET)
	@ApiOperation(value = "后台用户登出", notes = "")
	public RestResult<Void> backLogout(
			HttpServletRequest req) throws Exception {
		RestResult<Void> restResult = new RestResult<Void>();
		 req.getSession().removeAttribute("backUser");
		restResult.setState(200);
		return restResult;
	}
	
	
	@RequestMapping(value = "/frontLogout", method = RequestMethod.GET)
	@ApiOperation(value = "前台用户登出", notes = "")
	public RestResult<Void> frontLogout(
			HttpServletRequest req) throws Exception {
		RestResult<Void> restResult = new RestResult<Void>();
		req.getSession().removeAttribute("frontUser");
		restResult.setState(200);
		return restResult;
	}
	
	@RequestMapping(value = "/checkPhoneCode", method = RequestMethod.GET)
	@ApiOperation(value = "校验验证码", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", paramType = "query", defaultValue = "13925121452", value = "手机号", required = true),
			@ApiImplicitParam(name = "code", paramType = "query", defaultValue = "124521", value = "验证码", required = true) })
	public RestResult<HqUser> checkPhoneCode(@RequestParam String phone, @RequestParam String code,
			HttpServletRequest req) throws Exception {
		RestResult<HqUser> restResult = new RestResult<HqUser>();
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
			restResult.setState(500);
			restResult.setMsg("参数不能为空");
			return restResult;
		}
        try {
			GlobalRamCache.chekPhoneCode(phone, code);
		} catch (Exception e) {
			restResult.setState(500);
			restResult.setMsg(e.getMessage());
		}
        
		return restResult;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	@ApiOperation(value = "重置密码", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", paramType = "query", defaultValue = "13925121452", value = "手机号", required = true),
			@ApiImplicitParam(name = "newPassword", paramType = "query", defaultValue = "123456", value = "手机号", required = true),
			@ApiImplicitParam(name = "code", paramType = "query", defaultValue = "124521", value = "验证码", required = true) })
	public RestResult<HqUser> resetPassword(@RequestParam String phone, @RequestParam String code,@RequestParam String newPassword,
			HttpServletRequest req) throws Exception {
		RestResult<HqUser> restResult = new RestResult<HqUser>();
		if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)|| StringUtils.isBlank(newPassword)) {
			restResult.setState(500);
			restResult.setMsg("参数不能为空");
			return restResult;
		}
        try {
			GlobalRamCache.chekPhoneCode(phone, code);
			HqUser user=service.selectByPhone(phone);
			if (null==user) {
				restResult.setState(500);
				restResult.setMsg("不存在该手机号用户");
				return restResult;
			}
			
			user.setPassword(newPassword);
			service.update(user);
			GlobalRamCache.phoneCodeMap.remove(phone);
			restResult.setState(200);
			restResult.setMsg("成功");
		} catch (Exception e) {
			restResult.setState(500);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
}
