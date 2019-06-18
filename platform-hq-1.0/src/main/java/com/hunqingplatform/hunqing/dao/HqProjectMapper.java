package com.hunqingplatform.hunqing.dao;

import com.hunqingplatform.hunqing.dao.provider.HqProjectSqlProvider;
import com.hunqingplatform.hunqing.pojo.HqProject;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface HqProjectMapper {
    @Delete({
        "delete from hq_project",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);
    /**
     * 返回主键id
     * @param record
     * @return
     */
    @Insert({
        "insert into hq_project (id, projectName, ",
        "picUrls, videoUrls, ",
        "cmtDate, userId, ",
        "projectDesc)",
        "values (#{id,jdbcType=INTEGER}, #{projectName,jdbcType=VARCHAR}, ",
        "#{picUrls,jdbcType=VARCHAR}, #{videoUrls,jdbcType=VARCHAR}, ",
        "now(), #{userId,jdbcType=INTEGER}, ",
        "#{projectDesc,jdbcType=VARCHAR})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(HqProject record);

    @InsertProvider(type=HqProjectSqlProvider.class, method="insertSelective")
    int insertSelective(HqProject record);

    @Select({
        "select",
        "id, projectName, picUrls, videoUrls,  DATE_FORMAT(cmtDate,'%Y-%m-%d') cmtDate, userId, projectDesc",
        "from hq_project",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="projectName", property="projectName", jdbcType=JdbcType.VARCHAR),
        @Result(column="picUrls", property="picUrls", jdbcType=JdbcType.VARCHAR),
        @Result(column="videoUrls", property="videoUrls", jdbcType=JdbcType.VARCHAR),
        @Result(column="cmtDate", property="cmtDate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="userId", property="userId", jdbcType=JdbcType.INTEGER),
        @Result(column="projectDesc", property="projectDesc", jdbcType=JdbcType.VARCHAR)
    })
    HqProject selectByPrimaryKey(Integer id);

    @UpdateProvider(type=HqProjectSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(HqProject record);

    @Update({
        "update hq_project",
        "set projectName = #{projectName,jdbcType=VARCHAR},",
          "picUrls = #{picUrls,jdbcType=VARCHAR},",
          "videoUrls = #{videoUrls,jdbcType=VARCHAR},",
          "cmtDate = #{cmtDate,jdbcType=TIMESTAMP},",
          "userId = #{userId,jdbcType=INTEGER},",
          "projectDesc = #{projectDesc,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(HqProject record);
}