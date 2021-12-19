package com.example.mgdoll.model;

import java.math.BigDecimal;
import java.util.Date;

public class MgSuit {
    private Integer suitId;

    private String suitName;

    private Integer suitType;

    private Integer saleType;

    private BigDecimal price;

    private Integer ownerId;

    private Date insertTime;

    private Integer insertBy;

    public Integer getSuitId() {
        return suitId;
    }

    public void setSuitId(Integer suitId) {
        this.suitId = suitId;
    }

    public String getSuitName() {
        return suitName;
    }

    public void setSuitName(String suitName) {
        this.suitName = suitName == null ? null : suitName.trim();
    }

    public Integer getSuitType() {
        return suitType;
    }

    public void setSuitType(Integer suitType) {
        this.suitType = suitType;
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
}