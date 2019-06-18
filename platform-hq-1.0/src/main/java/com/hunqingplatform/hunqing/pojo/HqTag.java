package com.hunqingplatform.hunqing.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "标签创建model")
public class HqTag {
	@ApiModelProperty(name = "id", value = "主键", example = "1" ,required=false)
    private Integer id;

	@ApiModelProperty(name = "tagName", value = "标签名称", example = "1" ,required=true)
    private String tagName;

	@ApiModelProperty(name = "useStatus", value = "1启用 ，0停用", example = "1" ,required=false)
    private Integer useStatus;

	@ApiModelProperty(name = "userId", value = "创建者", example = "1" ,required=false,hidden=true)
    private Integer userId;

	@ApiModelProperty(name = "propNames", value = "标签属性", example = "1" ,required=true)
    private String propNames;

	@ApiModelProperty(name = "sortWeight", value = "排序", example = "1" ,required=false,hidden=true)
    private Integer sortWeight;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
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

    public Integer getSortWeight() {
        return sortWeight;
    }

    public void setSortWeight(Integer sortWeight) {
        this.sortWeight = sortWeight;
    }
}