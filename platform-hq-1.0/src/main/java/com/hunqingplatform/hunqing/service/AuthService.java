package com.hunqingplatform.hunqing.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hunqingplatform.hunqing.common.page.AbstractPageService;
import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.dao.ext.HqAuthMapperExt;
import com.hunqingplatform.hunqing.dao.ext.HqRoleAuthMapperExt;
import com.hunqingplatform.hunqing.dao.ext.HqUserMapperExt;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqAuth;
import com.hunqingplatform.hunqing.pojo.HqUser;

@Service
public class AuthService extends AbstractPageService<DaoSearch> {

	@Autowired
	private HqAuthMapperExt dao;
	
	@Autowired
	private HqRoleAuthMapperExt hqRoleAuthDao;
	
	@Autowired
	private HqUserMapperExt userDao;
	
	public List<HqAuth> getAll(){
		List<HqAuth> list=dao.getAll();
		return list;
	}


	public List<String> getUserAuth(int backUserId) throws CommonException {
		HqUser user=userDao.selectByPrimaryKey(backUserId);
		if (null==user) {
			throw new CommonException("不存在用户id为："+backUserId+"的用户", 500);
		}
		List<HqAuth> list=hqRoleAuthDao.selectByRoleId(user.getRoleId());
		List<String> strList=new ArrayList<String>();
		if (null!=list && !list.isEmpty()) {
			for (HqAuth hqAuth : list) {
				strList.add(hqAuth.getAuthName());
			}
		}
		return strList;
	}
}
