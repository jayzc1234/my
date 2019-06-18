package com.hunqingplatform.hunqing.dto;

import com.hunqingplatform.hunqing.common.page.DaoSearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "案例标签列表model")
public class HqProjectListDTO extends DaoSearch{
	@ApiModelProperty(name = "tagName", value = "过滤标签名", example = "风格")
    private String tagName;
    
	@ApiModelProperty(name = "tagPropName", value = "过滤标签值", example = "中式")
    private String tagPropName;

	@ApiModelProperty(name = "userId", value = "过滤标签值", example = "中式",hidden=true)
    private Integer userId;
	
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagPropName() {
		return tagPropName;
	}

	public void setTagPropName(String tagPropName) {
		this.tagPropName = tagPropName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
    
    
}