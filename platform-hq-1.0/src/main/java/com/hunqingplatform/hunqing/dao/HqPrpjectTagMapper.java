package com.hunqingplatform.hunqing.dao;

import com.hunqingplatform.hunqing.dao.provider.HqPrpjectTagSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqPrpjectTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

public interface HqPrpjectTagMapper {
    @Insert({
        "insert into hq_project_tag (id, projectId, ",
        "tagId, tagPropName)",
        "values (#{id,jdbcType=INTEGER}, #{projectId,jdbcType=INTEGER}, ",
        "#{tagId,jdbcType=INTEGER}, #{tagPropName,jdbcType=VARCHAR})"
    })
    int insert(HqPrpjectTag record);

    @InsertProvider(type=HqPrpjectTagSqlProvider.class, method="insertSelective")
    int insertSelective(HqPrpjectTag record);
}