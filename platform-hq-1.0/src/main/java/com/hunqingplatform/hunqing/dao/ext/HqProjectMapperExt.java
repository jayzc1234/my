package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqProjectMapper;
import com.hunqingplatform.hunqing.dto.HqProjectListDTO;
import com.hunqingplatform.hunqing.dto.MainPageSearchDTO;
import com.hunqingplatform.hunqing.pojo.HqProject;
import com.hunqingplatform.hunqing.vo.HqProjectVO;
@Mapper
public interface HqProjectMapperExt extends HqProjectMapper,CommonSql {

	
    @Select(startScript
            +"select"
            + " hp.id, hp.projectName,  hp.picUrls,hp.projectDesc,  hp.videoUrls,  DATE_FORMAT(hp.cmtDate,'%Y-%m-%d') cmtDate ,  hp.userId"
            +"  from hq_project hp left join hq_project_tag pt on hp.id=pt.projectId left join hq_tag ht on pt.tagId=ht.id"
            +"<where>"
            +"<if test='search!=null and search !=&apos;&apos; '>  hp.projectName LIKE CONCAT('%',#{search},'%')</if>"
            +"<if test='search==null or search ==&apos;&apos; '>"
	            + "<if test='tagName!=null and tagName !=&apos;&apos; '> and ht.tagName=#{tagName}</if>"
	            + "<if test='tagPropName!=null and tagPropName !=&apos;&apos; '> and pt.tagPropName=#{tagPropName}</if>"
            + "</if>"
            +"<if test='userId!=null '> and  hp.userId=#{userId}</if>"
            +"</where>"
            +" group by hp.id"
            +page
            +endScript
        )
	List<HqProjectVO> list(HqProjectListDTO searchParams);

    @Select(startScript
    		+" select count(*) from ("
            +"select"
            + " count(*)"
            +"  from hq_project hp left join hq_project_tag pt on hp.id=pt.projectId left join hq_tag ht on pt.tagId=ht.id"
            +"<where>"
            +"<if test='search!=null and search !=&apos;&apos; '>  hp.projectName LIKE CONCAT('%',#{search},'%')</if>"
            +"<if test='search==null or search ==&apos;&apos; '>"
	            + "<if test='tagName!=null and tagName !=&apos;&apos; '> and ht.tagName=#{tagName}</if>"
	            + "<if test='tagPropName!=null and tagPropName !=&apos;&apos; '> and pt.tagPropName=#{tagPropName}</if>"
            + "</if>"
            +"<if test='userId!=null '> and  hp.userId=#{userId}</if>"
            +"</where>"
            +" group by hp.id"
            +")a"
            +endScript
        )
	Integer count(HqProjectListDTO searchParams);

    @Select(startScript
    	    +"select"
            + " hp.id, hp.projectName,  hp.picUrls,hp.projectDesc,  hp.videoUrls,  DATE_FORMAT(hp.cmtDate,'%Y-%m-%d') cmtDate,  hp.userId"
            +" from hq_project hp left join hq_project_tag pt on hp.id=pt.projectId left join hq_tag ht on pt.tagId=ht.id"
    	    +"<where>"
    	    +"<if test='searchTags!=null and searchTags.size()>0'>"
            +" case"
            +" <foreach collection='searchTags' index='index' item='item' open='' separator='' close=''>" 
               +" when pt.tagId=#{item.tagId} then pt.tagPropName=#{item.tagPropName} "
            + "</foreach>"
            +" end"
               +"</if>"
               +"<if test='userId!=null '> and  hp.userId=#{userId}</if>"
               +"<if test='search!=null and search !=&apos;&apos; '> and hp.projectName LIKE CONCAT('%',#{search},'%')</if>"
            +"</where>"
            +" group by hp.id"
            +page
    		+endScript)
	List<HqProjectVO> mainSearch(MainPageSearchDTO searchParams);

    @Select(startScript
    		 +"select"
             + " count(*) from ("
    	    +"select"
            + " count(*)"
            +" from hq_project hp left join hq_project_tag pt on hp.id=pt.projectId left join hq_tag ht on pt.tagId=ht.id"
    	    +"<where>"
    	    +"<if test='searchTags!=null and searchTags.size()>0'>"
            +" case"
               +" <foreach collection='searchTags' index='index' item='item' open='' separator='' close=''>" 
                 +" when pt.tagId=#{item.tagId} then pt.tagPropName=#{item.tagPropName} "
                + "</foreach>"
                +" end"
            +"</if>"
            +"<if test='userId!=null '> and  hp.userId=#{userId}</if>"
               +"<if test='search!=null and search !=&apos;&apos; '> and hp.projectName LIKE CONCAT('%',#{search},'%')</if>"
            +"</where>"
            +" group by hp.id"
            +") a"
    		+endScript)
	int mainSearchcount(MainPageSearchDTO searchParams);

    @Select("select count(*) from hq_project where projectName=#{projectName} and userId=#{userId}")
	int countByProName(@Param("projectName")String projectName, @Param("userId")int userid);
    
}