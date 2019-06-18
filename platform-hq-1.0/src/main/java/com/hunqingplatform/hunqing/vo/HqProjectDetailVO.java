package com.hunqingplatform.hunqing.vo;

import java.util.Date;
import java.util.List;

public class HqProjectDetailVO {
    private Integer id;

    private String projectName;

    private String picUrls;

    private String videoUrls;

    private Date cmtDate;
    
    private String projectDesc;
  
    List<HqTagVO> tags;
    
    
	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public List<HqTagVO> getTags() {
		return tags;
	}

	public void setTags(List<HqTagVO> tags) {
		this.tags = tags;
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

    public Date getCmtDate() {
        return cmtDate;
    }

    public void setCmtDate(Date cmtDate) {
        this.cmtDate = cmtDate;
    }
}