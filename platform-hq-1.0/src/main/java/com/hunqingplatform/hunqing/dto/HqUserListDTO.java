package com.hunqingplatform.hunqing.dto;

import com.hunqingplatform.hunqing.common.page.DaoSearch;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "用户列表model")
public class HqUserListDTO extends DaoSearch{

	@ApiModelProperty(name = "roleId", value = "过滤的权限id", example = "1")
    private Integer roleId;

	@ApiModelProperty(name = "useStatus", value = "过滤的状态值，1启用 0禁用", example = "1")
    private Integer useStatus;
    
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}
	    
}
