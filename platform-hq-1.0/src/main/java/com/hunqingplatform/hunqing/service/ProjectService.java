package com.hunqingplatform.hunqing.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hunqingplatform.hunqing.common.page.AbstractPageService;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dao.ext.HqProjectMapperExt;
import com.hunqingplatform.hunqing.dao.ext.HqPrpjectTagMapperExt;
import com.hunqingplatform.hunqing.dto.HqProjectDTO;
import com.hunqingplatform.hunqing.dto.HqProjectListDTO;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqProject;
import com.hunqingplatform.hunqing.pojo.HqPrpjectTag;
import com.hunqingplatform.hunqing.vo.HqProjectDetailVO;
import com.hunqingplatform.hunqing.vo.HqProjectVO;
import com.hunqingplatform.hunqing.vo.HqTagVO;

@Service
@Transactional
public class ProjectService extends AbstractPageService<HqProjectListDTO> {
	@Autowired
	private HqProjectMapperExt dao;

	@Autowired
	private HqPrpjectTagMapperExt proTagDao;
	
	@Autowired
	private CommonUtil commonUtil;
	
	@Override
	protected List<HqProjectVO> searchResult(HqProjectListDTO searchParams) throws Exception {
		
        List<HqProjectVO> list=dao.list(searchParams);
		if (null!=list && !list.isEmpty()) {
			for (HqProjectVO hqProjectVO : list) {
				List<String> tagPropNames=proTagDao.selectTagProNamesByProId(hqProjectVO.getId());
				hqProjectVO.setTagPropNames(tagPropNames);
			}
		}
		return list;
	}

	@Override
	protected int count(HqProjectListDTO searchParams) throws Exception {
		Integer count=dao.count(searchParams);
		return count==null?0:count;
	}

	public void add(HqProjectDTO projectDTO,HttpServletRequest request) throws CommonException {
	  int userid=commonUtil.getBackUserId(request);
	  
	  int num=dao.countByProName(projectDTO.getProjectName(),userid);
	  if (num>=1) {
		throw new CommonException("已存在相同案例无法重复添加",500);
	  }
	  HqProject pro=new HqProject();
	  pro.setPicUrls(projectDTO.getPicUrls());
	  pro.setProjectName(projectDTO.getProjectName());
	  pro.setVideoUrls(projectDTO.getVideoUrls());
	  pro.setUserId(userid);
	  pro.setProjectDesc(projectDTO.getProjectDesc());
      dao.insert(pro);	
      
      
      List<HqPrpjectTag> tags=projectDTO.getProTags();
      for (HqPrpjectTag hqPrpjectTag : tags) {
    	  hqPrpjectTag.setProjectId(pro.getId());
    	  proTagDao.insert(hqPrpjectTag);
	  }
      
	}

	public void update(HqProjectDTO projectDTO,HttpServletRequest request) throws CommonException {
		 HqProject pro=new HqProject();
		  pro.setPicUrls(projectDTO.getPicUrls());
		  pro.setProjectName(projectDTO.getProjectName());
		  pro.setVideoUrls(projectDTO.getVideoUrls());
		  pro.setUserId(commonUtil.getBackUserId(request));
		  pro.setId(projectDTO.getId());
		  pro.setProjectDesc(projectDTO.getProjectDesc());
	      dao.updateByPrimaryKeySelective(pro);
	      
	      List<HqPrpjectTag> tags=projectDTO.getProTags();
	      if (null!=tags && !tags.isEmpty()) {
	    	  proTagDao.deleteByProId(projectDTO.getId());
	    	  for (HqPrpjectTag hqPrpjectTag : tags) {
	    		  proTagDao.insert(hqPrpjectTag);
	    	  }	
		  }
	}

	public void delete(List<Integer> ids) throws CommonException {
		if (null==ids || ids.isEmpty()) {
			throw new CommonException("ids不能为空", 500);
		}
      for (Integer id : ids) {
		dao.deleteByPrimaryKey(id);
		proTagDao.deleteByProId(id);
	  }
      
	}

	public HqProjectDetailVO detailProject(int id) throws CommonException {
       HqProject hqProject= dao.selectByPrimaryKey(id);
       if (null==hqProject) {
		throw new CommonException("方案不存在", 500);
	    }
       List<HqTagVO> tags=proTagDao.selectTagByProId(id);
       HqProjectDetailVO vo=new HqProjectDetailVO();
       vo.setCmtDate(hqProject.getCmtDate());
       vo.setId(id);
       vo.setPicUrls(hqProject.getPicUrls());
       vo.setVideoUrls(hqProject.getVideoUrls());
       vo.setTags(tags);
       vo.setProjectName(hqProject.getProjectName());
       vo.setProjectDesc(hqProject.getProjectDesc());
       return vo;
       
	}

}
