package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqPrpjectTagMapper;
import com.hunqingplatform.hunqing.vo.HqTagVO;
@Mapper
public interface HqPrpjectTagMapperExt extends HqPrpjectTagMapper,CommonSql {
    @Delete("delete from hq_project_tag where projectId=#{projectId}")
	void deleteByProId(@Param("projectId")Integer projectId);

    @Select("select tagPropName from hq_project_tag where projectId=#{projectId}")
	List<String> selectTagProNamesByProId(@Param("projectId")Integer projectId);

    @Select("select ht.id,ht.tagName,ht.propNames,pt.tagPropName as currentValue  from hq_project_tag pt  left join hq_tag ht on pt.tagId=ht.id where projectId=#{projectId}")
	List<HqTagVO> selectTagByProId(@Param("projectId")Integer projectId);

}