package com.hunqingplatform.hunqing.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hunqingplatform.hunqing.common.page.AbstractPageService;
import com.hunqingplatform.hunqing.common.util.CommonUtil;
import com.hunqingplatform.hunqing.dao.ext.HqUserMapperExt;
import com.hunqingplatform.hunqing.dto.HqUserListDTO;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqUser;

@Component
public class HqUserService extends AbstractPageService<HqUserListDTO> {
	@Autowired
	private HqUserMapperExt dao;

	public HqUser selectByPhoneAndPassword(String phone, String password) throws CommonException {
		String encryPass = md5ToLower(password);
		HqUser user = dao.selectByPhoneAndPassword(phone, encryPass);
		return user;
	}

	@Override
	protected List<HqUser> searchResult(HqUserListDTO searchParams) throws Exception {
		String sortOrder=searchParams.getSortOrder();
		String order=searchParams.getOrder();
		int startNumber = (searchParams.getCurrent()-1)*searchParams.getPageSize();
		searchParams.setStartNumber(startNumber);
		if (StringUtils.isBlank(sortOrder) && StringUtils.isBlank(order)) {
			order="cmtDate";
			sortOrder="asc";
		}
		List<HqUser> list=dao.list(searchParams);
		return list;
	}

	@Override
	protected int count(HqUserListDTO searchParams) throws Exception {
		return dao.count(searchParams);
	}

	public HqUser selectByPhone(String phone) {
		return dao.selectByPhone(phone);
	}

	public void update(HqUser user) throws CommonException {
		HqUser euser=dao.selectByPrimaryKey(user.getId());
		if (null==euser) {
			throw new CommonException("用户不存在", 500);
		}
		String password=user.getPassword();
		if (StringUtils.isNotBlank(password)) {
			if (!euser.getPassword().equals(password)) {
				user.setPassword(md5ToLower(password));
			}
		}
		dao.updateByPrimaryKey(user);
	}

	public void add(HqUser user) throws CommonException {
		HqUser euser=dao.selectByPhone(user.getPhone());
		if (null!=euser) {
			throw new CommonException("已存在该手机号用户", 500);
		}
		user.setPassword(CommonUtil.md5ToLower(user.getPassword()));
		dao.insert(user);		
	}

	public void delete(int id) {
      dao.deleteByPrimaryKey(id);		
	}

	public HqUser selectById(int id) {
		return dao.selectByPrimaryKey(id);
	}

}
