package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqRole;

public class HqRoleSqlProvider {

    public String insertSelective(HqRole record) {
        BEGIN();
        INSERT_INTO("hq_role");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getRoleName() != null) {
            VALUES("roleName", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getUseStatus() != null) {
            VALUES("useStatus", "#{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            VALUES("userId", "#{userId,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqRole record) {
        BEGIN();
        UPDATE("hq_role");
        
        if (record.getRoleName() != null) {
            SET("roleName = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getUseStatus() != null) {
            SET("useStatus = #{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getUserId() != null) {
            SET("userId = #{userId,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}