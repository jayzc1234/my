package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hunqingplatform.hunqing.common.page.DaoSearch;
import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqTagMapper;
import com.hunqingplatform.hunqing.pojo.HqTag;
@Mapper
public interface HqTagMapperExt extends HqTagMapper ,CommonSql{
	
    @Insert({
        "insert into hq_tag (id, tagName, ",
        "useStatus, userId, ",
        "propNames, sortWeight)",
        "values (#{id,jdbcType=INTEGER}, #{tagName,jdbcType=VARCHAR}, ",
        "#{useStatus,jdbcType=INTEGER}, #{userId,jdbcType=INTEGER}, ",
        "#{propNames,jdbcType=VARCHAR}, #{sortWeight,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(HqTag record);

    @Select(startScript
            +"select"
            + " id, tagName, useStatus, userId, propNames, sortWeight"
            +" from hq_tag"
            +"<where> "
            +"<if test='search!=null and search !=&apos;&apos; '> tagName LIKE CONCAT('%',#{search},'%')</if>"
            +"<if test='useStatus!=null '>and useStatus=#{useStatus}</if>"
            +"</where>"
            +"order by sortWeight desc"
            +page
            +endScript
        )
	List<HqTag> list(DaoSearch searchParams);

    @Select(startScript
            +"select"
            + " count(*)"
            +" from hq_tag"
            +"<where>"
            +"<if test='search!=null and search !=&apos;&apos; '> tagName LIKE CONCAT('%',#{search},'%')</if>"
            +"<if test='useStatus!=null '>and useStatus=#{useStatus}</if>"
            +"</where>"
            +endScript
        )
	int count(DaoSearch searchParams);

    @Select(startScript
            +"select"
            + " id, tagName, useStatus, userId, propNames, sortWeight"
            +" from hq_tag"
            +"<where> "
            +"<if test='type==1'>sortWeight &gt;#{sortWeight} </if>"
            +"<if test='type==0'>sortWeight &lt;#{sortWeight} </if>"
            +"</where>"
            +"order by sortWeight desc"
            +" limit #{num}"
            +endScript
        )
	List<HqTag> selectExchangeTag(@Param("sortWeight")int sortWeight, @Param("num")int num, @Param("type")int type);

    @Select({
        "select ",
        " id, tagName, useStatus, userId, propNames, sortWeight",
        " from hq_tag",
        "where tagName = #{tagName}"
    })
	HqTag selectByTagName(@Param("tagName")String tagName);

    @Select({
        "select ",
        " id, tagName, useStatus, userId, propNames, sortWeight",
        " from hq_tag",
    })
	List<HqTag> selectByUserId(int frontUserId);
    
}