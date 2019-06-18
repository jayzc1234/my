package com.hunqingplatform.hunqing.dao;

/**
 * 描述：公共sql模块
 *
 * @author corbett
 *         Created by corbett on 2018/10/15.
 */
public interface CommonSql {
    String select = " SELECT ";
    String startScript = "<script>";
    String endScript = "</script>";
    String count = "count(*)";
    String page = "<if test='startNumber != null  and pageSize != null '> LIMIT #{startNumber},#{pageSize}</if>";
    String page_limit = "LIMIT #{startNum},#{pageSize}";
}
