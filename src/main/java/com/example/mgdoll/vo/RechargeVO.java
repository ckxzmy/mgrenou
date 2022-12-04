package com.example.mgdoll.vo;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RechargeVO {
    @ApiModelProperty(required = true)
    private String userId;

    @ApiModelProperty(required = true,value = "add/minus")
    private String flag;

    @ApiModelProperty(required = true,value = "金额")
    private BigDecimal amount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
