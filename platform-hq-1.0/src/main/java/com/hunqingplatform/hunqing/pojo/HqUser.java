package com.hunqingplatform.hunqing.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "新增用户model")
public class HqUser {
	@ApiModelProperty(name = "id", value = "主键", example = "1",required=false)
    private Integer id;

	@ApiModelProperty(name = "userName", value = "用户名", example = "1",required=false)
    private String userName;

	@ApiModelProperty(name = "phone", value = "手机", example = "1",required=false)
    private String phone;

	@ApiModelProperty(name = "password", value = "密码", example = "1",required=false)
    private String password;

	@ApiModelProperty(name = "roleId", value = "权限id", example = "1",required=false)
    private Integer roleId;

	@ApiModelProperty(name = "useStatus", value = "使用状态，1在使用，0被停用，更新时需要", example = "1")
    private Integer useStatus;

	@ApiModelProperty(name = "cmtDate", value = "创建时间", example = "1")
    private String cmtDate;

    private String descText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public String getCmtDate() {
		return cmtDate;
	}

	public void setCmtDate(String cmtDate) {
		this.cmtDate = cmtDate;
	}

	public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }
}