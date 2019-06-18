package com.hunqingplatform.hunqing.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "权限创建model")
public class HqRoleDTO {
    @ApiModelProperty(name = "id", value = "主键，修改时需要", example = "1")
    private Integer id;
    
    @ApiModelProperty(name = "roleName", value = "权限名称", example = "风格")
    private String roleName;

    @ApiModelProperty(name = "useStatus", value = "使用状态，1在使用，0被停用，更新时需要", example = "1")
    private Integer useStatus;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(Integer useStatus) {
		this.useStatus = useStatus;
	}

	public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}