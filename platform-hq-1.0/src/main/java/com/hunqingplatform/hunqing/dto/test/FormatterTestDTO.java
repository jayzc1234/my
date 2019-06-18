package com.hunqingplatform.hunqing.dto.test;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "测试formatter的model")
public class FormatterTestDTO {
	@ApiModelProperty(name = "id", value = "主键", example = "1" ,required=false)
    private Integer id;

	@ApiModelProperty(name = "cmtDate", value = "创建时间", example = "2019-05-06" ,required=true)
//	@DateTimeFormat(pattern="yyyy-MM-dd")
    private String cmtDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCmtDate() {
		return cmtDate;
	}

	public void setCmtDate(String cmtDate) {
		this.cmtDate = cmtDate;
	}

 

}