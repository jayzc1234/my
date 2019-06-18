package com.hunqingplatform.hunqing.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.cache.GlobalRamCache;
import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.page.AbstractPageService.PageResults;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dto.HqUserListDTO;
import com.hunqingplatform.hunqing.pojo.HqUser;
import com.hunqingplatform.hunqing.service.HqUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/back/user")
@Api(tags = "后台用户接口")
public class BackUserController extends CommonUtil{
	@Autowired
	private HqUserService service;
	
	@Autowired
	private CommonUtil commonUtil;
	
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ApiOperation(value = "新增用户", notes = "")
    public RestResult<String> add(@RequestBody HqUser user) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (StringUtils.isBlank(user.getPhone()) || null==user.getRoleId() || StringUtils.isBlank(user.getUserName())) {
    		restResult.setState(500);
    		restResult.setMsg("参数不能为空");
    		return restResult;
		}
    	user.setUseStatus(1);
    	service.add(user);
    	restResult.setState(200);
		return restResult;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST)
    @ApiOperation(value = "修改用户", notes = "")
    public RestResult<String> update(@RequestBody HqUser user) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (null==user.getId()) {
    		restResult.setState(500);
    		restResult.setMsg("id不为空");
    		return restResult;
		}
    	restResult.setState(200);
    	service.update(user);
		return restResult;
    }
    
    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    @ApiOperation(value = "删除用户", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", paramType = "query", defaultValue = "1", value = "主键id", required = true),
    })
    public RestResult<String> delete(@RequestParam int id ) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	restResult.setState(200);
    	service.delete(id);
		return restResult;
    }
    
    @RequestMapping(value = "/page",method=RequestMethod.GET)
    @ApiOperation(value = "用户列表", notes = "")
    public RestResult<PageResults<List<HqUser>>> page(HqUserListDTO daoSearch) throws Exception {
    	RestResult<PageResults<List<HqUser>>> restResult=new RestResult<PageResults<List<HqUser>>>();
    	PageResults<List<HqUser>> pageResults=service.listSearchViewLike(daoSearch);
    	restResult.setState(200);
    	restResult.setResults(pageResults);
    	return restResult;
    }

    @RequestMapping(value = "/resetPhone", method = RequestMethod.GET)
	@ApiOperation(value = "重置手机号", notes = "")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "newphone", paramType = "query", defaultValue = "13925121452", value = "手机号", required = true),
			@ApiImplicitParam(name = "code", paramType = "query", defaultValue = "124521", value = "验证码", required = true) })
	public RestResult<HqUser> resetPhone(@RequestParam String newphone, @RequestParam String code,
			HttpServletRequest req) throws Exception {
		RestResult<HqUser> restResult = new RestResult<HqUser>();
		if (StringUtils.isBlank(newphone) || StringUtils.isBlank(code)) {
			restResult.setState(500);
			restResult.setMsg("参数不能为空");
			return restResult;
		}
        try {
			GlobalRamCache.chekPhoneCode(newphone, code);
			HqUser user=service.selectById(commonUtil.getBackUserId(req));
            user.setPhone(newphone);
			service.update(user);
			req.getSession().setAttribute("backUser", user);
			GlobalRamCache.phoneCodeMap.remove(newphone);
			restResult.setState(200);
			restResult.setMsg("成功");
		} catch (Exception e) {
			restResult.setState(500);
			restResult.setMsg(e.getMessage());
		}
		return restResult;
	}
}
