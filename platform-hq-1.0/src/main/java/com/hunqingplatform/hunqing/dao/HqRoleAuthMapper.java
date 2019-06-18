package com.hunqingplatform.hunqing.dao;

import com.hunqingplatform.hunqing.dao.provider.HqRoleAuthSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqRoleAuth;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HqRoleAuthMapper {
    @Delete({
        "delete from hq_role_auth",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into hq_role_auth (id, roleId, ",
        "authId)",
        "values (#{id,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{authId,jdbcType=INTEGER})"
    })
    int insert(HqRoleAuth record);

    @InsertProvider(type=HqRoleAuthSqlProvider.class, method="insertSelective")
    int insertSelective(HqRoleAuth record);

    @Select({
        "select",
        "id, roleId, authId",
        "from hq_role_auth",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="authId", property="authId", jdbcType=JdbcType.INTEGER)
    })
    HqRoleAuth selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqRoleAuthSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqRoleAuth record);

    @Update({
        "update hq_role_auth",
        "set roleId = #{roleId,jdbcType=INTEGER},",
          "authId = #{authId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HqRoleAuth record);
}