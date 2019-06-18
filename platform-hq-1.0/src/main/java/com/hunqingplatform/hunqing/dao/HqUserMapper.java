package com.hunqingplatform.hunqing.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import com.hunqingplatform.hunqing.dao.provider.HqUserSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqUser;

public interface HqUserMapper {
    @Delete({
        "delete from hq_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into hq_user (id, userName, ",
        "phone, password, ",
        "roleId, useStatus, ",
        "cmtDate, descText)",
        "values (#{id,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, ",
        "#{phone,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{roleId,jdbcType=INTEGER}, #{useStatus,jdbcType=INTEGER}, ",
        "now(), #{descText,jdbcType=VARCHAR})"
    })
    int insert(HqUser record);

    @InsertProvider(type=HqUserSqlProvider.class, method="insertSelective")
    int insertSelective(HqUser record);

    @Select({
        "select",
        "id, userName, phone, password, roleId, useStatus, DATE_FORMAT(cmtDate,'%Y-%m-%d'), descText",
        "from hq_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="userName", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="roleId", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="useStatus", property="useStatus", jdbcType=JdbcType.INTEGER),
        @Result(column="cmtDate", property="cmtDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="descText", property="descText", jdbcType=JdbcType.VARCHAR)
    })
    HqUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqUser record);

    @Update({
        "update hq_user",
        "set userName = #{userName,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "roleId = #{roleId,jdbcType=INTEGER},",
          "useStatus = #{useStatus,jdbcType=INTEGER},",
          "cmtDate = #{cmtDate,jdbcType=TIMESTAMP},",
          "descText = #{descText,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HqUser record);
}