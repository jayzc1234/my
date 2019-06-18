package com.hunqingplatform.hunqing.controller.test;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dto.test.FormatterTestDTO;
import com.hunqingplatform.hunqing.dto.test.FormatterTestDTO2;
import com.hunqingplatform.hunqing.pojo.HqAuth;
import com.hunqingplatform.hunqing.service.AuthService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/unauth/test")
@Api(tags = "测试接口")
public class TestController {
	
	@Autowired
	private AuthService service;
	
	@Autowired
	private CommonUtil commonUtil;
	
    @RequestMapping(value = "/get",method=RequestMethod.POST)
    @ApiOperation(value = "测试date类型formatter", notes = "")
    public RestResult<List<HqAuth>> getall(@RequestBody FormatterTestDTO fmt) throws Exception {
    	RestResult<List<HqAuth>> restResult = new RestResult<List<HqAuth>>();
    	System.out.println(fmt);
    	restResult.setState(200);
		return restResult;
    }

}
