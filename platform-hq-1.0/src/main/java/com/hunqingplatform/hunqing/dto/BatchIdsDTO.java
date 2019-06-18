package com.hunqingplatform.hunqing.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "批量操作id集合model")
public class BatchIdsDTO {
 @ApiModelProperty(name = "ids", value = "主键集合" ,required=false)
 private List<Integer> ids;

public List<Integer> getIds() {
	return ids;
}

public void setIds(List<Integer> ids) {
	this.ids = ids;
}
 
 
}
