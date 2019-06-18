package com.hunqingplatform.hunqing.dao;

import com.hunqingplatform.hunqing.dao.provider.HqAuthSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqAuth;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HqAuthMapper {
    @Delete({
        "delete from hq_auth",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into hq_auth (id, authName)",
        "values (#{id,jdbcType=INTEGER}, #{authName,jdbcType=VARCHAR})"
    })
    int insert(HqAuth record);

    @InsertProvider(type=HqAuthSqlProvider.class, method="insertSelective")
    int insertSelective(HqAuth record);

    @Select({
        "select",
        "id, authName",
        "from hq_auth",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="authName", property="authName", jdbcType=JdbcType.VARCHAR)
    })
    HqAuth selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqAuthSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqAuth record);

    @Update({
        "update hq_auth",
        "set authName = #{authName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HqAuth record);
}