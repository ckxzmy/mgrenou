package com.example.mgdoll.model;

import java.util.Date;

public class MgSuitDetail {
    private Integer detailId;

    private Integer suitId;

    private Integer detailType;

    private Integer detailContentId;

    private Integer contentType;

    private Date insertTime;

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getSuitId() {
        return suitId;
    }

    public void setSuitId(Integer suitId) {
        this.suitId = suitId;
    }

    public Integer getDetailType() {
        return detailType;
    }

    public void setDetailType(Integer detailType) {
        this.detailType = detailType;
    }

    public Integer getDetailContentId() {
        return detailContentId;
    }

    public void setDetailContentId(Integer detailContentId) {
        this.detailContentId = detailContentId;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}