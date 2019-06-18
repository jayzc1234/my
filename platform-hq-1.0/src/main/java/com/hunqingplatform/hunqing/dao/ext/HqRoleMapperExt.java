package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqRoleMapper;
import com.hunqingplatform.hunqing.pojo.HqRole;
@Mapper
public interface HqRoleMapperExt extends HqRoleMapper,CommonSql{

    @Select(startScript
        +"select"
        +" id, roleName, useStatus, userId"
        +" from hq_role"
        +"<if test='search!=null and search !=&apos;&apos; '>where roleName LIKE CONCAT('%',#{search},'%')</if>"
        +page
        +endScript
    )
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleName", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="useStatus", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER)
    })
	List<HqRole> list(DaoSearch searchParams);

    
    @Select(startScript
            +"select"
            +" count(*)"
            +" from hq_role"
            +"<if test='search!=null and search !=&apos;&apos; '>where roleName LIKE CONCAT('%',#{search},'%')</if>"
            +endScript
        )
	int count(DaoSearch searchParams);

    @Select({
        "select",
        " id, roleName, useStatus, userId",
        "from hq_role",
        "where roleName = #{roleName}"
    })
	HqRole selectByRoleName(@Param("roleName")String roleName);
}