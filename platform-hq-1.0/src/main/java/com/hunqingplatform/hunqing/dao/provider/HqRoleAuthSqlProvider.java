package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqRoleAuth;

public class HqRoleAuthSqlProvider {

    public String insertSelective(HqRoleAuth record) {
        BEGIN();
        INSERT_INTO("hq_role_auth");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            VALUES("roleId", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getAuthId() != null) {
            VALUES("authId", "#{authId,jdbcType=INTEGER}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqRoleAuth record) {
        BEGIN();
        UPDATE("hq_role_auth");
        
        if (record.getRoleId() != null) {
            SET("roleId = #{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getAuthId() != null) {
            SET("authId = #{authId,jdbcType=INTEGER}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}