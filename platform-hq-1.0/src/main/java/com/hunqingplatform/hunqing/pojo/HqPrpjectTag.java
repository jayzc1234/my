package com.hunqingplatform.hunqing.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "案例标签创建model")
public class HqPrpjectTag {
	@ApiModelProperty(name = "id", value = "主键", example = "1")
    private Integer id;

	@ApiModelProperty(name = "projectId", value = "案例id", example = "1",hidden=true)
    private Integer projectId;

    @ApiModelProperty(name = "tagId", value = "标签id", example = "1")
    private Integer tagId;

    @ApiModelProperty(name = "tagPropName", value = "标签值", example = "中式")
    private String tagPropName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    public String getTagPropName() {
        return tagPropName;
    }

    public void setTagPropName(String tagPropName) {
        this.tagPropName = tagPropName;
    }
}