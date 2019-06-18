package com.hunqingplatform.hunqing.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "新增权限model")
public class HqRole {
	@ApiModelProperty(name = "id", value = "z主键", example = "1",required=false)
    private Integer id;

	@ApiModelProperty(name = "roleName", value = "角色名称", example = "1",required=true)
    private String roleName;

	@ApiModelProperty(name = "useStatus", value = "1启用，0停用", example = "1")
    private Integer useStatus;

    @ApiModelProperty(name = "userId", value = "创建人", example = "1")
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getUseStatus() {
        return useStatus;
    }

    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}