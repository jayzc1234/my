package com.hunqingplatform.hunqing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "主页过滤标签model")
public class MainSearchTagDTO {
	@ApiModelProperty(name = "tagId", value = "标签id", example = "1")
	private int tagId;
	  
	@ApiModelProperty(name = "tagPropName", value = "标签值", example = "1")
	private String tagPropName;

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public String getTagPropName() {
		return tagPropName;
	}

	public void setTagPropName(String tagPropName) {
		this.tagPropName = tagPropName;
	}
}
