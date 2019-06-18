package com.hunqingplatform.hunqing.controller.front;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hunqingplatform.hunqing.common.model.RestResult;
import com.hunqingplatform.hunqing.common.page.AbstractPageService.PageResults;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dto.MainPageSearchDTO;
import com.hunqingplatform.hunqing.pojo.HqTag;
import com.hunqingplatform.hunqing.service.MainPageService;
import com.hunqingplatform.hunqing.service.TagService;
import com.hunqingplatform.hunqing.vo.HqProjectVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/auth/front/mainpage")
@Api(tags = "前端主页接口")
public class FrontMainPageController {
	
	@Autowired
	private MainPageService service;
	
	@Autowired
	private TagService tagService;
	
	@Autowired
	private CommonUtil commonUtil;
	
    @RequestMapping(value = "/page",method=RequestMethod.POST)
    @ApiOperation(value = "方案列表", notes = "")
    public RestResult<PageResults<List<HqProjectVO>>> sendPhoneCode(@RequestBody MainPageSearchDTO pListDTO,HttpServletRequest request) throws Exception {
    	pListDTO.setUserId(commonUtil.getFrontUserId(request));
    	RestResult<PageResults<List<HqProjectVO>>> restResult=new RestResult<PageResults<List<HqProjectVO>>>();
    	 PageResults<List<HqProjectVO>> pageResults= service.listSearchViewLike(pListDTO);
    	 restResult.setResults(pageResults);
    	 restResult.setState(200);
    	 return restResult;
    	 
    }

    @RequestMapping(value = "/getTags",method=RequestMethod.GET)
    @ApiOperation(value = "获取搜索标签", notes = "")
    public RestResult<List<HqTag>> page(HttpServletRequest request) throws Exception {
    	RestResult<List<HqTag>> restResult=new RestResult<List<HqTag>>();
    	List<HqTag> list=tagService.getAll(commonUtil.getFrontUserId(request));
    	restResult.setState(200);
    	restResult.setResults(list);
    	return restResult;
    }
}
