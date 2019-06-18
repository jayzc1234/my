package com.hunqingplatform.hunqing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hunqingplatform.hunqing.common.page.AbstractPageService;
import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.dao.ext.HqRoleAuthMapperExt;
import com.hunqingplatform.hunqing.dao.ext.HqRoleMapperExt;
import com.hunqingplatform.hunqing.exception.CommonException;
import com.hunqingplatform.hunqing.pojo.HqRole;
import com.hunqingplatform.hunqing.pojo.HqRoleAuth;
import com.hunqingplatform.hunqing.vo.HqAuthVO;
import com.hunqingplatform.hunqing.vo.HqRoleDetailVO;

@Service
@Transactional
public class RoleService extends AbstractPageService<DaoSearch> {
	@Autowired
	private HqRoleMapperExt dao;

	@Autowired
	private HqRoleAuthMapperExt hqRoleAuthDao;

	public int add(HqRoleDetailVO hDetailVO) throws CommonException {
		HqRole erole = dao.selectByRoleName(hDetailVO.getRoleName());
		if (null != erole) {
			throw new CommonException("相同名称权限已存在", 500);
		}
		List<HqAuthVO> auths = hDetailVO.getOwnAuths();
		HqRole role = new HqRole();
		role.setRoleName(hDetailVO.getRoleName());
		role.setUseStatus(1);
		int i = dao.insert(role);

		if (null != auths && !auths.isEmpty()) {
			for (HqAuthVO hqAuthVO : auths) {
				HqRoleAuth roleAuth = new HqRoleAuth();
				roleAuth.setAuthId(hqAuthVO.getId());
				roleAuth.setRoleId(role.getId());
				hqRoleAuthDao.insert(roleAuth);
			}
		}
		return i;
	}

	public void update(HqRoleDetailVO hDetailVO) {
		HqRole role = new HqRole();
		role.setRoleName(hDetailVO.getRoleName());
		role.setUseStatus(1);
		role.setId(hDetailVO.getId());
		dao.updateByPrimaryKey(role);
		List<HqAuthVO> auths = hDetailVO.getOwnAuths();
		if (null != auths && !auths.isEmpty()) {
			hqRoleAuthDao.deleteByRoleId(hDetailVO.getId());
			for (HqAuthVO hqAuthVO : auths) {
				HqRoleAuth roleAuth = new HqRoleAuth();
				roleAuth.setAuthId(hqAuthVO.getId());
				roleAuth.setRoleId(role.getId());
				hqRoleAuthDao.insert(roleAuth);
			}

		}
	}

	@Override
	protected List<HqRole> searchResult(DaoSearch searchParams) throws Exception {
		List<HqRole> list = dao.list(searchParams);
		return list;
	}

	@Override
	protected int count(DaoSearch searchParams) throws Exception {
		return dao.count(searchParams);
	}

	public void delete(int id) {
		dao.deleteByPrimaryKey(id);
	}

	public HqRoleDetailVO detail(int roleId) {
		HqRole role = dao.selectByPrimaryKey(roleId);
		List<HqAuthVO> voAuthVOs = hqRoleAuthDao.selectRoleAuthInAllAuth(roleId);
		HqRoleDetailVO vo = new HqRoleDetailVO();
		vo.setId(role.getId());
		vo.setOwnAuths(voAuthVOs);
		vo.setRoleName(role.getRoleName());
		vo.setUseStatus(role.getUseStatus());
		return vo;
	}

}
