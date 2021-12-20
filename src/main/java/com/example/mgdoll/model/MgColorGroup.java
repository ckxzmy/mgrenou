package com.example.mgdoll.model;

import java.util.Date;
import java.util.List;

public class MgColorGroup {
    private Integer groupId;

    private String groupName;

    private Date insertTime;

    private Integer insertBy;

    private List<MgColor> colorArray;

    public List<MgColor> getColorArray() {
        return colorArray;
    }

    public void setColorArray(List<MgColor> colorArray) {
        this.colorArray = colorArray;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(Integer insertBy) {
        this.insertBy = insertBy;
    }
}