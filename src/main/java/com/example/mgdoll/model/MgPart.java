package com.example.mgdoll.model;

import java.util.Date;
import java.util.List;

public class MgPart {
    private Integer partId;

    private String partName;

    private String partType;

    private Integer dollId;

    private Integer colorGroupId;
    private String colorGroupName;

    private Date insertTime;

    private String insertBy;
    private Integer status;

    private List<LayerModel> layerList;
    private List<MgCategory> categoryList;

    public List<LayerModel> getLayerList() {
        return layerList;
    }

    public void setLayerList(List<LayerModel> layerList) {
        this.layerList = layerList;
    }

    public List<MgCategory> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<MgCategory> categoryList) {
        this.categoryList = categoryList;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName == null ? null : partName.trim();
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType == null ? null : partType.trim();
    }

    public Integer getDollId() {
        return dollId;
    }

    public void setDollId(Integer dollId) {
        this.dollId = dollId;
    }

    public Integer getColorGroupId() {
        return colorGroupId;
    }

    public void setColorGroupId(Integer colorGroupId) {
        this.colorGroupId = colorGroupId;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getInsertBy() {
        return insertBy;
    }

    public void setInsertBy(String insertBy) {
        this.insertBy = insertBy;
    }

    public String getColorGroupName() {
        return colorGroupName;
    }

    public void setColorGroupName(String colorGroupName) {
        this.colorGroupName = colorGroupName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}