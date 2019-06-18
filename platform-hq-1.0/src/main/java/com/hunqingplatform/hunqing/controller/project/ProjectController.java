package com.hunqingplatform.hunqing.controller.project;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.page.AbstractPageService.PageResults;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dto.BatchIdsDTO;
import com.hunqingplatform.hunqing.dto.HqProjectDTO;
import com.hunqingplatform.hunqing.dto.HqProjectListDTO;
import com.hunqingplatform.hunqing.pojo.HqProject;
import com.hunqingplatform.hunqing.service.ProjectService;
import com.hunqingplatform.hunqing.vo.HqProjectDetailVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/back/project")
@Api(tags = "方案接口")
public class ProjectController {
	
	@Autowired
	private ProjectService service;
	
	
	@Autowired
	private CommonUtil commonUtil;
	
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ApiOperation(value = "新增方案", notes = "")
    public RestResult<String> add(@Valid @RequestBody HqProjectDTO projectDTO,HttpServletRequest request) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (StringUtils.isBlank(projectDTO.getProjectName()) || null==projectDTO.getProTags() || projectDTO.getProTags().isEmpty()) {
    		restResult.setState(500);
    		restResult.setMsg("方案名称和标签不为空");
    		return restResult;
		}
    	
    	restResult.setState(200);
    	service.add(projectDTO, request);
		return restResult;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST)
    @ApiOperation(value = "修改方案", notes = "")
    public RestResult<String> update(@RequestBody HqProjectDTO projectDTO,HttpServletRequest request) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (null==projectDTO.getId()) {
    		restResult.setState(500);
    		restResult.setMsg("id不为空");
    		return restResult;
		}
    	restResult.setState(200);
    	service.update(projectDTO,request);
		return restResult;
    }
    
    @RequestMapping(value = "/delete",method=RequestMethod.POST)
    @ApiOperation(value = "删除方案", notes = "")
    public RestResult<String> delete(@RequestBody BatchIdsDTO batchIdsDTO ) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	restResult.setState(200);
    	try {
    		service.delete(batchIdsDTO.getIds());
		} catch (Exception e) {
			restResult.setState(500);
			restResult.setMsg(e.getMessage());
			return restResult;
		}
		return restResult;
    }

    @RequestMapping(value = "/detail",method=RequestMethod.GET)
    @ApiOperation(value = "方案查看/编辑获取方案信息接口", notes = "")
    public RestResult<HqProjectDetailVO> find(@RequestParam  int id) throws Exception {
    	RestResult<HqProjectDetailVO> restResult = new RestResult<HqProjectDetailVO>();
    	HqProjectDetailVO hDetailVO=service.detailProject(id);
    	restResult.setResults(hDetailVO);
    	restResult.setState(200);
    	return restResult;
    }
    
    @RequestMapping(value = "/page",method=RequestMethod.GET)
    @ApiOperation(value = "方案列表", notes = "")
    public RestResult<PageResults<List<HqProject>>> page(HqProjectListDTO pListDTO,HttpServletRequest request) throws Exception {
    	int userid=commonUtil.getBackUserId(request);
    	pListDTO.setUserId(userid);
    	RestResult<PageResults<List<HqProject>>> restResult=new RestResult<PageResults<List<HqProject>>>();
    	PageResults<List<HqProject>> pageResults=service.listSearchViewLike(pListDTO);
    	restResult.setState(200);
    	restResult.setResults(pageResults);
    	return restResult;
    }

}
