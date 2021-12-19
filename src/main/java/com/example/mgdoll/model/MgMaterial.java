package com.example.mgdoll.model;

import java.util.Date;

public class MgMaterial {
    private Integer materialId;

    private String materialName;

    private Integer layerLevel;

    private Integer categoryId;

    private Date insertTime;

    private Integer insertBy;

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName == null ? null : materialName.trim();
    }

    public Integer getLayerLevel() {
        return layerLevel;
    }

    public void setLayerLevel(Integer layerLevel) {
        this.layerLevel = layerLevel;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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