package com.hunqingplatform.hunqing.vo;

import java.util.List;

public class HqRoleDetailVO {
    private Integer id;

    private String roleName;

    private Integer useStatus;

    private Integer userId;
    
    private List<HqAuthVO> ownAuths;
    

    public List<HqAuthVO> getOwnAuths() {
		return ownAuths;
	}

	public void setOwnAuths(List<HqAuthVO> ownAuths) {
		this.ownAuths = ownAuths;
	}

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