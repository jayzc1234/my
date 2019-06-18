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

import com.hunqingplatform.hunqing.dao.provider.HqTagSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqTag;

public interface HqTagMapper {
    @Delete({
        "delete from hq_tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);


    @InsertProvider(type=HqTagSqlProvider.class, method="insertSelective")
    int insertSelective(HqTag record);

    @Select({
        "select",
        " id, tagName, useStatus, userId, propNames, sortWeight",
        " from hq_tag",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tagName", property="tagName", jdbcType=JdbcType.VARCHAR),
        @Result(column="useStatus", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="propNames", property="propNames", jdbcType=JdbcType.VARCHAR),
        @Result(column="sortWeight", property="sortWeight", jdbcType=JdbcType.INTEGER)
    })
    HqTag selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqTagSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqTag record);

    @Update({
        "update hq_tag",
        "set tagName = #{tagName,jdbcType=VARCHAR},",
          "useStatus = #{useStatus,jdbcType=INTEGER},",
          "userId = #{userId,jdbcType=INTEGER},",
          "propNames = #{propNames,jdbcType=VARCHAR},",
          "sortWeight = #{sortWeight,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HqTag record);
}