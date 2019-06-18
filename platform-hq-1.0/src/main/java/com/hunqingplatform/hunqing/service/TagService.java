package com.hunqingplatform.hunqing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hunqingplatform.hunqing.common.page.AbstractPageService;
import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.dao.ext.HqTagMapperExt;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqTag;

@Service
public class TagService extends AbstractPageService<DaoSearch> {
	@Autowired
	private HqTagMapperExt dao;

	public int add(HqTag tag) throws CommonException {
	  HqTag etag=dao.selectByTagName(tag.getTagName());
	  if (null!=etag) {
		throw new CommonException("同名标签已存在", 500);
	  }
      int i= dao.insert(tag);	
      tag.setSortWeight(tag.getId());
      dao.updateByPrimaryKey(tag);
      return i;
	}

	public void update(HqTag tag) {
      dao.updateByPrimaryKey(tag);		
	}

	@Override
	protected List<HqTag> searchResult(DaoSearch searchParams) throws Exception {
		List<HqTag> list=dao.list(searchParams);
		return list;
	}

	@Override
	protected int count(DaoSearch searchParams) throws Exception {
		return dao.count(searchParams);
	}

	public void delete(int id) {
		dao.deleteByPrimaryKey(id);
	}

	@Transactional
	public void sort(int id, int num, int type) {
         HqTag ctag=dao.selectByPrimaryKey(id);
         
         List<HqTag> list=dao.selectExchangeTag(ctag.getSortWeight(),num,type);
         if (null!=list && !list.isEmpty()) {
        	 HqTag extag=null;
        	 if (type==1) {
        		 extag=list.get(0);
			 }else {
				 extag=list.get(list.size()-1);
			 }
        	 int c_sortW=ctag.getSortWeight();
        	 int ex_sortW=extag.getSortWeight();
        	 
        	 ctag.setSortWeight(ex_sortW);
        	 
        	 extag.setSortWeight(c_sortW);
        	 
        	 dao.updateByPrimaryKey(ctag);
        	 
        	 dao.updateByPrimaryKey(extag);
		}
	}

	public List<HqTag> getAll(int frontUserId) {
		return dao.selectByUserId(frontUserId);
	}

	
}
