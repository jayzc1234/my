package com.hunqingplatform.hunqing.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.hunqingplatform.hunqing.pojo.HqPrpjectTag;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value = "案例创建model")
public class HqProjectDTO {
	@ApiModelProperty(name = "id", value = "主键", example = "1" ,required=false)
    private Integer id;

	@ApiModelProperty(name = "projectName", value = "案例名称", example = "1" ,required=true)
	@NotEmpty
    private String projectName;

	@ApiModelProperty(name = "picUrls", value = "案例图片地址集合，格式前端自定义", example = "" ,required=true)
    private String picUrls;

	@ApiModelProperty(name = "videoUrls", value = "案例视频地址集合，格式前端自定义", example = "" ,required=true)
    private String videoUrls;
	
	@ApiModelProperty(name = "projectDesc", value = "案例描述", example = "1" ,required=true)
	private String projectDesc;
	
	@ApiModelProperty(name = "proTags", value = "标签", example = "" ,required=true)
    private List<HqPrpjectTag> proTags;


    public Integer getId() {
        return id;
    }
    

    public String getProjectDesc() {
		return projectDesc;
	}


	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}


	public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getPicUrls() {
        return picUrls;
    }
    

    public List<HqPrpjectTag> getProTags() {
		return proTags;
	}

	public void setProTags(List<HqPrpjectTag> proTags) {
		this.proTags = proTags;
	}

	public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public String getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(String videoUrls) {
        this.videoUrls = videoUrls;
    }


}