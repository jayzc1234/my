package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqRoleAuthMapper;
import com.hunqingplatform.hunqing.pojo.HqAuth;
import com.hunqingplatform.hunqing.vo.HqAuthVO;
@Mapper
public interface HqRoleAuthMapperExt extends HqRoleAuthMapper,CommonSql{

    @Select({
        "select",
        "a.id, a.authName",
        "from hq_role_auth ra left join hq_auth a on ra.authId=a.id ",
        "where ra.roleId = #{roleId,jdbcType=INTEGER}"
    })
	List<HqAuth> selectByRoleId(@Param("roleId")int roleId);

    @Select({
        "select",
        "a.id, a.authName ,if(ra.id is null,0,1) as owned ",
        "from hq_role_auth ra left join hq_auth a on ra.authId=a.id ",
        "where ra.roleId = #{roleId,jdbcType=INTEGER}"
    })
	List<HqAuthVO> selectRoleAuthInAllAuth(@Param("roleId")int roleId);

    @Delete({
        "delete from hq_role_auth",
        "where roleId = #{roleId,jdbcType=INTEGER}"
    })
	void deleteByRoleId(@Param("roleId")Integer roleId);
  
}