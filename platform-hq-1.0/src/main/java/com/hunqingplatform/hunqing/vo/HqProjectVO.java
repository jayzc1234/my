package com.hunqingplatform.hunqing.vo;

import java.util.Date;
import java.util.List;

public class HqProjectVO {
    private Integer id;

    private String projectName;

    private String picUrls;

    private String videoUrls;

    private String cmtDate;

    private List<String> tagPropNames;    
    
    private String projectDesc;
    
	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public List<String> getTagPropNames() {
		return tagPropNames;
	}

	public void setTagPropNames(List<String> tagPropNames) {
		this.tagPropNames = tagPropNames;
	}

	public Integer getId() {
        return id;
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

    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }

    public String getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(String videoUrls) {
        this.videoUrls = videoUrls;
    }

	public String getCmtDate() {
		return cmtDate;
	}

	public void setCmtDate(String cmtDate) {
		this.cmtDate = cmtDate;
	}


}