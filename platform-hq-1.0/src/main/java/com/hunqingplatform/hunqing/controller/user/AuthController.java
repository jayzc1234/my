package com.hunqingplatform.hunqing.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.pojo.HqAuth;
import com.hunqingplatform.hunqing.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/back/auth")
@Api(tags = "权限接口")
public class AuthController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private CommonUtil commonUtil;
	
    @RequestMapping(value = "/getAll",method=RequestMethod.GET)
    @ApiOperation(value = "获取所有权限对应的功能", notes = "")
    public RestResult<List<HqAuth>> getall() throws Exception {
    	RestResult<List<HqAuth>> restResult = new RestResult<List<HqAuth>>();
        restResult.setResults(service.getAll());
    	restResult.setState(200);
		return restResult;
    }
    
    
    @RequestMapping(value = "/getUserAuth",method=RequestMethod.GET)
    @ApiOperation(value = "获取用户权限", notes = "")
    public RestResult<List<String>> getUserAuth(HttpServletRequest request) throws Exception {
    	RestResult<List<String>> restResult = new RestResult<List<String>>();
        restResult.setResults(service.getUserAuth(commonUtil.getBackUserId(request)));
    	restResult.setState(200);
		return restResult;
    }
}
