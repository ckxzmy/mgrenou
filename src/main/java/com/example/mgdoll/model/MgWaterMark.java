package com.example.mgdoll.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MgWaterMark {
    private Integer id;

    private String userId;

    private String waterUrl;

    private Integer status;
    private String statusName;

    private Integer createBy;

    private Date createTime;

    private String userPetName;

    @ApiModelProperty(required = true,value = "删除时必填，审核时必填")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @ApiModelProperty(required = true,value = "新增时必填,审核时必填")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    @ApiModelProperty(required = true,value = "新增时必填")
    public String getWaterUrl() {
        return waterUrl;
    }

    public void setWaterUrl(String waterUrl) {
        this.waterUrl = waterUrl == null ? null : waterUrl.trim();
    }

    @ApiModelProperty(required = true,value = "审核时必填，通过置为0，不通过置为2")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getUserPetName() {
        return userPetName;
    }

    public void setUserPetName(String userPetName) {
        this.userPetName = userPetName;
    }
}