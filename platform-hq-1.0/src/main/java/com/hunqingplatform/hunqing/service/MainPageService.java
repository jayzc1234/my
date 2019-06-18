package com.hunqingplatform.hunqing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hunqingplatform.hunqing.common.page.AbstractPageService;
import com.hunqingplatform.hunqing.dao.ext.HqProjectMapperExt;
import com.hunqingplatform.hunqing.dao.ext.HqPrpjectTagMapperExt;
import com.hunqingplatform.hunqing.dto.MainPageSearchDTO;
import com.hunqingplatform.hunqing.pojo.HqProject;
import com.hunqingplatform.hunqing.vo.HqProjectVO;

@Service
public class MainPageService extends AbstractPageService<MainPageSearchDTO>{
	

	@Autowired
	private HqProjectMapperExt projectDao;
	
	@Autowired
	private HqPrpjectTagMapperExt proTagDao;
	@Override
	protected List<HqProjectVO> searchResult(MainPageSearchDTO searchParams) throws Exception {
        List<HqProjectVO> list=projectDao.mainSearch(searchParams);
		if (null!=list && !list.isEmpty()) {
			for (HqProjectVO hqProjectVO : list) {
				List<String> tagPropNames=proTagDao.selectTagProNamesByProId(hqProjectVO.getId());
				hqProjectVO.setTagPropNames(tagPropNames);
			}
		}
		return list;
	}

	@Override
	protected int count(MainPageSearchDTO searchParams) throws Exception {
		return projectDao.mainSearchcount(searchParams);
	}
   
}
