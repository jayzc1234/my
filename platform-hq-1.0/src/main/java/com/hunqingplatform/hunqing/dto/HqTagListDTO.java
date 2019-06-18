package com.hunqingplatform.hunqing.dto;

import com.hunqingplatform.hunqing.common.page.DaoSearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "标签列表model")
public class HqTagListDTO extends DaoSearch{
	@ApiModelProperty(name = "useStatus", value = "使用状态，1在使用，0被停用，更新时需要", example = "1")
    private Integer useStatus;

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

}