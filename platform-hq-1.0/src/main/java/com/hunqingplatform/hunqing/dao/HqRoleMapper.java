package com.hunqingplatform.hunqing.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.hunqingplatform.hunqing.dao.provider.HqRoleSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqRole;

public interface HqRoleMapper extends CommonSql{
    @Delete({
        "delete from hq_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into hq_role (id, roleName, ",
        "useStatus, userId)",
        "values (#{id,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR}, ",
        "#{useStatus,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(HqRole record);

    @InsertProvider(type=HqRoleSqlProvider.class, method="insertSelective")
    int insertSelective(HqRole record);

    @Select({
        "select",
        "id, roleName, useStatus, userId",
        "from hq_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleName", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="useStatus", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER)
    })
    HqRole selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqRole record);

    @Update(startScript
       + "update hq_role"
        +"<set>"
	       +"<if test='roleName !=null and roleName != &apos;&apos; '> roleName = #{roleName,jdbcType=VARCHAR},</if>"
	       +"<if test='userId !=null'>  userId = #{userId,jdbcType=INTEGER},</if>"
	       +"<if test='useStatus !=null '>useStatus = #{useStatus,jdbcType=INTEGER},</if>"
        +"</set>"
        +"where id = #{id,jdbcType=INTEGER}"
        +endScript
    )
    int updateByPrimaryKey(HqRole record);

}