package com.hunqingplatform.hunqing.dto;

import java.util.List;

import com.hunqingplatform.hunqing.common.page.DaoSearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "主页搜索model")
public class MainPageSearchDTO extends DaoSearch {
	@ApiModelProperty(name = "userId", value = "过滤标签名", example = "风格",hidden=true)
  private Integer userId;
  
  @ApiModelProperty(name = "searchTags", value = "过滤标签名")
  private List<MainSearchTagDTO> searchTags;

public List<MainSearchTagDTO> getSearchTags() {
	return searchTags;
}

public void setSearchTags(List<MainSearchTagDTO> searchTags) {
	this.searchTags = searchTags;
}

public Integer getUserId() {
	return userId;
}

public void setUserId(Integer userId) {
	this.userId = userId;
}
  
  
  
  
}
