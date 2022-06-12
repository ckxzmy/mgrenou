package com.example.mgdoll.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MgCategory {
    private Integer categoryId;

    private String categoryName;

    private Integer categoryType;
    private String categoryTypeString;

    private Integer saleType;
    private String saleTypeString;

    private BigDecimal price;

    private Integer partId;

    private Integer colorId;

    private Integer suitId;

    private String ownerId;

    private Date insertTime;

    private String insertBy;

    private Integer status;

    private List<MgMaterial> materialList;

    public List<MgMaterial> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(List<MgMaterial> materialList) {
        this.materialList = materialList;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(Integer categoryType) {
        this.categoryType = categoryType;
    }

    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public Integer getSuitId() {
        return suitId;
    }

    public void setSuitId(Integer suitId) {
        this.suitId = suitId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
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

    public String getCategoryTypeString() {
        return categoryTypeString;
    }

    public void setCategoryTypeString(String categoryTypeString) {
        this.categoryTypeString = categoryTypeString;
    }

    public String getSaleTypeString() {
        return saleTypeString;
    }

    public void setSaleTypeString(String saleTypeString) {
        this.saleTypeString = saleTypeString;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}