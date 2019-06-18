package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqUserMapper;
import com.hunqingplatform.hunqing.dto.HqUserListDTO;
import com.hunqingplatform.hunqing.pojo.HqUser;
@Mapper
public interface HqUserMapperExt extends HqUserMapper,CommonSql{
   
    @Select({
        "select",
        " id, userName, phone, password, roleId, useStatus, DATE_FORMAT(cmtDate,'%Y-%m-%d') cmtDate, descText",
        " from hq_user",
        " where phone = #{phone,jdbcType=VARCHAR} and password = #{password,jdbcType=VARCHAR}"
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
	HqUser selectByPhoneAndPassword(@Param("phone")String phone, @Param("password")String password);

    @Select({
        "select",
        " id, userName, phone, password, roleId, useStatus, DATE_FORMAT(cmtDate,'%Y-%m-%d') cmtDate, descText",
        " from hq_user",
        " where phone = #{phone,jdbcType=VARCHAR}"
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
	HqUser selectByPhone(@Param("phone")String phone);
    
    
    @Select(startScript
        +"select"
         +"  id, userName, phone, password, roleId, useStatus, DATE_FORMAT(cmtDate,'%Y-%m-%d') cmtDate, descText"
         +" from hq_user"
         +"<where>"
         + " <if test='useStatus !=null '> useStatus = #{useStatus}</if> "
         + " <if test='roleId !=null '> and roleId = #{roleId}</if> "
         +"  <if test='search!=null and search !=&apos;&apos; '> and ( phone LIKE CONCAT('%',#{search},'%') or userName LIKE CONCAT('%',#{search},'%'))</if>"
         + "</where>"
         + " <if test='order !=null and order != &apos;&apos; and sortOrder !=null and sortOrder != &apos;&apos;  '> order by ${order} ${sortOrder}</if> "
         +page
         +endScript
    )
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
	List<HqUser> list(HqUserListDTO searchParams);

    @Select(startScript
            +"select"
             +" count(*)"
             +" from hq_user"
             +"<where>"
	             + " <if test='useStatus !=null '> useStatus = #{useStatus}</if> "
	             + " <if test='roleId !=null '> and roleId = #{roleId}</if> "
	             +"  <if test='search!=null and search !=&apos;&apos; '> and ( phone LIKE CONCAT('%',#{search},'%') or userName LIKE CONCAT('%',#{search},'%'))</if>"
             + "</where>"
             +endScript
        )
	int count(HqUserListDTO searchParams);
}