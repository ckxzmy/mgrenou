package com.example.mgdoll.model;

import java.math.BigDecimal;
import java.util.Date;

public class MgColor {
    private Integer colorId;

    private String colorName;

    private Integer colorType;
    private String colorTypeString;

    private Integer saleType;
    private String saleTypeString;

    private BigDecimal price;

    private Integer groupId;

    private String ownerId;

    private Date insertTime;

    private String insertBy;

    private Integer status;

    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName == null ? null : colorName.trim();
    }

    public Integer getColorType() {
        return colorType;
    }

    public void setColorType(Integer colorType) {
        this.colorType = colorType;
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

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
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

    public String getColorTypeString() {
        return colorTypeString;
    }

    public void setColorTypeString(String colorTypeString) {
        this.colorTypeString = colorTypeString;
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