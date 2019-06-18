package com.hunqingplatform.hunqing.vo;

public class HqTagVO {
    private Integer id;

    private String tagName;

    private Integer useStatus;

    private Integer userId;

    private String propNames;
    
    private String currentValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public void setTagName(String tagName) {
        this.tagName = tagName;
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

    public String getPropNames() {
        return propNames;
    }

    public void setPropNames(String propNames) {
        this.propNames = propNames;
    }
}