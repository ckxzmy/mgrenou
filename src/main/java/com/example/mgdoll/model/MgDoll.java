package com.example.mgdoll.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MgDoll {
    private Integer dollId;

    private String dollName;

    private Integer dollType;
    private String dollTypeString;

    private Integer saleType;
    private String saleTypeString;

    private BigDecimal price;

    private Integer ownerId;

    private Date insertTime;

    private Integer insertBy;

    private List<MgColorGroup> colorList;
    private List<MgPart> partList;

    public List<MgColorGroup> getColorList() {
        return colorList;
    }

    public void setColorList(List<MgColorGroup> colorList) {
        this.colorList = colorList;
    }

    public List<MgPart> getPartList() {
        return partList;
    }

    public void setPartList(List<MgPart> partList) {
        this.partList = partList;
    }

    public Integer getDollId() {
        return dollId;
    }

    public void setDollId(Integer dollId) {
        this.dollId = dollId;
    }

    public String getDollName() {
        return dollName;
    }

    public void setDollName(String dollName) {
        this.dollName = dollName == null ? null : dollName.trim();
    }

    public Integer getDollType() {
        return dollType;
    }

    public void setDollType(Integer dollType) {
        this.dollType = dollType;
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

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
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

    public String getDollTypeString() {
        return dollTypeString;
    }

    public void setDollTypeString(String dollTypeString) {
        this.dollTypeString = dollTypeString;
    }

    public String getSaleTypeString() {
        return saleTypeString;
    }

    public void setSaleTypeString(String saleTypeString) {
        this.saleTypeString = saleTypeString;
    }
}