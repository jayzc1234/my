package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqTag;

public class HqTagSqlProvider {

    public String insertSelective(HqTag record) {
        BEGIN();
        INSERT_INTO("hq_tag");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTagName() != null) {
            VALUES("tagName", "#{tagName,jdbcType=VARCHAR}");
        }
        
        if (record.getUseStatus() != null) {
            VALUES("useStatus", "#{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            VALUES("userId", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getPropNames() != null) {
            VALUES("propNames", "#{propNames,jdbcType=VARCHAR}");
        }
        
        if (record.getSortWeight() != null) {
            VALUES("sortWeight", "#{sortWeight,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqTag record) {
        BEGIN();
        UPDATE("hq_tag");
        
        if (record.getTagName() != null) {
            SET("tagName = #{tagName,jdbcType=VARCHAR}");
        }
        
        if (record.getUseStatus() != null) {
            SET("useStatus = #{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            SET("userId = #{userId,jdbcType=INTEGER}");
        }
        
        if (record.getPropNames() != null) {
            SET("propNames = #{propNames,jdbcType=VARCHAR}");
        }
        
        if (record.getSortWeight() != null) {
            SET("sortWeight = #{sortWeight,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}