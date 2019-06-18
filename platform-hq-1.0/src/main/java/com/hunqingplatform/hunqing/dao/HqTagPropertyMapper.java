package com.hunqingplatform.hunqing.dao;

import com.hunqingplatform.hunqing.dao.provider.HqTagPropertySqlProvider;
import com.hunqingplatform.hunqing.pojo.HqTagProperty;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HqTagPropertyMapper {
    @Delete({
        "delete from hq_tag_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into hq_tag_property (id, tagProName, ",
        "tagId, userStatus)",
        "values (#{id,jdbcType=INTEGER}, #{tagProName,jdbcType=VARCHAR}, ",
        "#{tagId,jdbcType=INTEGER}, #{userStatus,jdbcType=INTEGER})"
    })
    int insert(HqTagProperty record);

    @InsertProvider(type=HqTagPropertySqlProvider.class, method="insertSelective")
    int insertSelective(HqTagProperty record);

    @Select({
        "select",
        "id, tagProName, tagId, userStatus",
        "from hq_tag_property",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="tagProName", property="tagProName", jdbcType=JdbcType.VARCHAR),
        @Result(column="tagId", property="tagId", jdbcType=JdbcType.INTEGER),
        @Result(column="userStatus", property="userStatus", jdbcType=JdbcType.INTEGER)
    })
    HqTagProperty selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqTagPropertySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqTagProperty record);

    @Update({
        "update hq_tag_property",
        "set tagProName = #{tagProName,jdbcType=VARCHAR},",
          "tagId = #{tagId,jdbcType=INTEGER},",
          "userStatus = #{userStatus,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HqTagProperty record);
}