package com.hunqingplatform.hunqing.controller.project;

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
import com.hunqingplatform.hunqing.dto.HqTagListDTO;
import com.hunqingplatform.hunqing.pojo.HqTag;
import com.hunqingplatform.hunqing.service.TagService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/back/tag")
@Api(tags = "标签接口")
public class TagController {
	
	@Autowired
	private TagService service;
	
    @RequestMapping(value = "/add",method=RequestMethod.POST)
    @ApiOperation(value = "新增标签", notes = "")
    public RestResult<String> add(@RequestBody HqTag tag) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (StringUtils.isBlank(tag.getTagName())) {
    		restResult.setState(500);
    		restResult.setMsg("标签名称不为空");
    		return restResult;
		}
    	restResult.setState(200);
    	service.add(tag);
		return restResult;
    }
    
    @RequestMapping(value = "/update",method=RequestMethod.POST)
    @ApiOperation(value = "修改标签", notes = "")
    public RestResult<String> update(@RequestBody HqTag tag) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	if (null==tag.getId()) {
    		restResult.setState(500);
    		restResult.setMsg("id不为空");
    		return restResult;
		}
    	restResult.setState(200);
    	service.update(tag);
		return restResult;
    }
    
    @RequestMapping(value = "/delete",method=RequestMethod.GET)
    @ApiOperation(value = "删除标签", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",dataType="int", paramType = "query", defaultValue = "1", value = "标签主键id", required = true),
    })
    public RestResult<String> delete(@RequestParam int id ) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	restResult.setState(200);
    	service.delete(id);
		return restResult;
    }
    	
    @RequestMapping(value = "/page",method=RequestMethod.GET)
    @ApiOperation(value = "标签列表", notes = "")
    public RestResult<PageResults<List<HqTag>>> page(HqTagListDTO daoSearch) throws Exception {
    	RestResult<PageResults<List<HqTag>>> restResult=new RestResult<PageResults<List<HqTag>>>();
    	PageResults<List<HqTag>> pageResults=service.listSearchViewLike(daoSearch);
    	restResult.setState(200);
    	restResult.setResults(pageResults);
    	return restResult;
    }

    @RequestMapping(value = "/sort",method=RequestMethod.GET)
    @ApiOperation(value = "标签排序", notes = "")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id",dataType="int", paramType = "query", defaultValue = "1", value = "标签主键id", required = true),
        @ApiImplicitParam(name = "num",dataType="int", paramType = "query", defaultValue = "1", value = "上移或下移数量", required = true),
        @ApiImplicitParam(name = "type",dataType="int", paramType = "query", defaultValue = "1", value = "1上移，0下移", required = true),
    })
    public RestResult<String> sort(@RequestParam int id,@RequestParam int num,@RequestParam int type ) throws Exception {
    	RestResult<String> restResult = new RestResult<String>();
    	restResult.setState(200);
    	service.sort(id,num,type);
		return restResult;
    }
}
