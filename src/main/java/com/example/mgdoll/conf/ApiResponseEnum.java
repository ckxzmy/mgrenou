package com.example.mgdoll.conf;

/**
 * web 层返回信息枚举
 */
public enum ApiResponseEnum {
    /**
     * API调用成功返回
     */
    SUCCESS(10000,"请求成功"),
    FAIL(10001,"请求失败"),
    LOGIN_FAIL(10099,"登陆失败"),
    AUTH_ERROR(10100,"登录失败"),
    TOKEN_EXPIRE(10200,"Token过期"),
    TOKEN_FAIL(10201,"Token不存在");

    private int errCode = 0;

    private String errMsg;


    private ApiResponseEnum(int errCode, String errMsg) {
       this.errCode = errCode;
       this.errMsg = errMsg;
    }

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}