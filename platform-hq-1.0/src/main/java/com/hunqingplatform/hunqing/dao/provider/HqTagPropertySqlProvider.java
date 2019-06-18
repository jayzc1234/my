package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqTagProperty;

public class HqTagPropertySqlProvider {

    public String insertSelective(HqTagProperty record) {
        BEGIN();
        INSERT_INTO("hq_tag_property");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getTagProName() != null) {
            VALUES("tagProName", "#{tagProName,jdbcType=VARCHAR}");
        }
        
        if (record.getTagId() != null) {
            VALUES("tagId", "#{tagId,jdbcType=INTEGER}");
        }
        
        if (record.getUserStatus() != null) {
            VALUES("userStatus", "#{userStatus,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqTagProperty record) {
        BEGIN();
        UPDATE("hq_tag_property");
        
        if (record.getTagProName() != null) {
            SET("tagProName = #{tagProName,jdbcType=VARCHAR}");
        }
        
        if (record.getTagId() != null) {
            SET("tagId = #{tagId,jdbcType=INTEGER}");
        }
        
        if (record.getUserStatus() != null) {
            SET("userStatus = #{userStatus,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}