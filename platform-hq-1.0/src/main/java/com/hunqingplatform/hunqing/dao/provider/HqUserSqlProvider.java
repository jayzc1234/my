package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqUser;

public class HqUserSqlProvider {

    public String insertSelective(HqUser record) {
        BEGIN();
        INSERT_INTO("hq_user");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getUserName() != null) {
            VALUES("userName", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            VALUES("roleId", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getUseStatus() != null) {
            VALUES("useStatus", "#{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getCmtDate() != null) {
            VALUES("cmtDate", "#{cmtDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDescText() != null) {
            VALUES("descText", "#{descText,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqUser record) {
        BEGIN();
        UPDATE("hq_user");
        
        if (record.getUserName() != null) {
            SET("userName = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getRoleId() != null) {
            SET("roleId = #{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getUseStatus() != null) {
            SET("useStatus = #{useStatus,jdbcType=INTEGER}");
        }
        
        if (record.getCmtDate() != null) {
            SET("cmtDate = #{cmtDate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getDescText() != null) {
            SET("descText = #{descText,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}