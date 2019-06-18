package com.hunqingplatform.hunqing.dao.ext;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hunqingplatform.hunqing.dao.CommonSql;
import com.hunqingplatform.hunqing.dao.HqAuthMapper;
import com.hunqingplatform.hunqing.pojo.HqAuth;
@Mapper
public interface HqAuthMapperExt extends HqAuthMapper,CommonSql{


    @Select({
        "select",
        "id, authName",
        "from hq_auth",
    })
	List<HqAuth> getAll();
}