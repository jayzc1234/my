package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqProject;

public class HqProjectSqlProvider {

    public String insertSelective(HqProject record) {
        BEGIN();
        INSERT_INTO("hq_project");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProjectName() != null) {
            VALUES("projectName", "#{projectName,jdbcType=VARCHAR}");
        }
        
        if (record.getPicUrls() != null) {
            VALUES("picUrls", "#{picUrls,jdbcType=VARCHAR}");
        }
        
        if (record.getVideoUrls() != null) {
            VALUES("videoUrls", "#{videoUrls,jdbcType=VARCHAR}");
        }
        
        if (record.getCmtDate() != null) {
            VALUES("cmtDate", "#{cmtDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            VALUES("userId", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getProjectDesc() != null) {
            VALUES("projectDesc", "#{projectDesc,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqProject record) {
        BEGIN();
        UPDATE("hq_project");
        
        if (record.getProjectName() != null) {
            SET("projectName = #{projectName,jdbcType=VARCHAR}");
        }
        
        if (record.getPicUrls() != null) {
            SET("picUrls = #{picUrls,jdbcType=VARCHAR}");
        }
        
        if (record.getVideoUrls() != null) {
            SET("videoUrls = #{videoUrls,jdbcType=VARCHAR}");
        }
        
        if (record.getCmtDate() != null) {
            SET("cmtDate = #{cmtDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUserId() != null) {
            SET("userId = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getProjectDesc() != null) {
            SET("projectDesc = #{projectDesc,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}