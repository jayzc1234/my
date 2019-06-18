package com.hunqingplatform.hunqing.dao.provider;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;

import com.hunqingplatform.hunqing.pojo.HqPrpjectTag;

public class HqPrpjectTagSqlProvider {

    public String insertSelective(HqPrpjectTag record) {
        BEGIN();
        INSERT_INTO("hq_project_tag");
        
        if (record.getId() != null) {
            VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getProjectId() != null) {
            VALUES("projectId", "#{projectId,jdbcType=INTEGER}");
        }
        
        if (record.getTagId() != null) {
            VALUES("tagId", "#{tagId,jdbcType=INTEGER}");
        }
        
        if (record.getTagPropName() != null) {
            VALUES("tagPropName", "#{tagPropName,jdbcType=VARCHAR}");
        }
        
        return SQL();
    }
}