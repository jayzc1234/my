package com.hunqingplatform.hunqing.controller.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.page.AbstractPageService.PageResults;
import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.pojo.HqRole;
import com.hunqingplatform.hunqing.service.RoleService;
import com.hunqingplatform.hunqing.vo.HqRoleDetailVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/back/role")
@Api(tags = "权限接口")
public class RoleController {
	
	@Autowired
	private RoleService service;
	
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ApiOperation(value = "新增权限", notes = "")
    public RestResult<String> add(@RequestBody HqRoleDetailVO role) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (StringUtils.isBlank(role.getRoleName())) {
    		restResult.setState(500);
    		restResult.setMsg("权限名称不为空");
    		return restResult;
		}
    	restResult.setState(200);
    	service.add(role);
		return restResult;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST)
    @ApiOperation(value = "修改权限", notes = "")
    public RestResult<String> update(@RequestBody HqRoleDetailVO role) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (null==role.getId()) {
    		restResult.setState(500);
    		restResult.setMsg("id不为空");
    		return restResult;
		}
    	restResult.setState(200);
    	service.update(role);
		return restResult;
    }
    
    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    @ApiOperation(value = "删除权限", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", paramType = "query", defaultValue = "1", value = "权限主键id", required = true),
    })
    public RestResult<String> delete(@RequestParam int id ) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	restResult.setState(200);
    	service.delete(id);
		return restResult;
    }
    
    @RequestMapping(value = "/page",method=RequestMethod.POST)
    @ApiOperation(value = "权限列表", notes = "")
    public RestResult<PageResults<List<HqRole>>> page(DaoSearch daoSearch) throws Exception {
    	RestResult<PageResults<List<HqRole>>> restResult=new RestResult<PageResults<List<HqRole>>>();
    	PageResults<List<HqRole>> pageResults=service.listSearchViewLike(daoSearch);
    	restResult.setState(200);
    	restResult.setResults(pageResults);
    	return restResult;
    }

    @RequestMapping(value = "/detail",method=RequestMethod.GET)
    @ApiOperation(value = "权限详情", notes = "")
    public RestResult<HqRoleDetailVO> detail(@RequestParam int id) throws Exception {
    	RestResult<HqRoleDetailVO> restResult = new RestResult<HqRoleDetailVO>();
    	restResult.setState(200);
    	HqRoleDetailVO hqRoleDetailVO= service.detail(id);
    	restResult.setResults(hqRoleDetailVO);
		return restResult;
    }
}
