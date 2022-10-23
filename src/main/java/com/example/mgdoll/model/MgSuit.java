package com.example.mgdoll.model;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MgSuit {
    private Integer suitId;

    private String suitName;

    private Integer suitType;

    private Integer saleType;

    private BigDecimal price;

    private String ownerId;

    private Date insertTime;

    private String insertBy;

    private Integer status;

    private List<MgSuitDetail> mgSuitDetails;

    @ApiModelProperty(required = true,value = "新增时不填,修改时必填")
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

    @ApiModelProperty(value = "套装销售类型，")
    public Integer getSuitType() {
        return suitType;
    }

    public void setSuitType(Integer suitType) {
        this.suitType = suitType;
    }

    @ApiModelProperty(value = "售卖货币类型，1余额2积分")
    public Integer getSaleType() {
        return saleType;
    }

    public void setSaleType(Integer saleType) {
        this.saleType = saleType;
    }

    @ApiModelProperty(value = "价格")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId == null ? null : ownerId.trim();
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
        this.insertBy = insertBy == null ? null : insertBy.trim();
    }

    @ApiModelProperty(value = "-1.删除 0.正常 1.上架 2.下架")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MgSuitDetail> getMgSuitDetails() {
        return mgSuitDetails;
    }

    public void setMgSuitDetails(List<MgSuitDetail> mgSuitDetails) {
        this.mgSuitDetails = mgSuitDetails;
    }
}