package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.hunqingplatform.hunqing.pojo.HqAuth;

public class HqAuthSqlProvider {

    public String insertSelective(HqAuth record) {
        BEGIN();
        INSERT_INTO("hq_auth");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getAuthName() != null) {
            VALUES("authName", "#{authName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }

    public String updateByPrimaryKeySelective(HqAuth record) {
        BEGIN();
        UPDATE("hq_auth");
        
        if (record.getAuthName() != null) {
            SET("authName = #{authName,jdbcType=VARCHAR}");
        }
        
        WHERE("id = #{id,jdbcType=INTEGER}");
        
        return SQL();
    }
}