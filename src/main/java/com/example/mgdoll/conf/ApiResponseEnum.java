package com.example.mgdoll.conf;

/**
 * web 层返回信息枚举
 */
public enum ApiResponseEnum {
    /**
     * API调用成功返回
     */
    SUCCESS(0,"请求成功"),
    FAIL(-1,"请求失败"),
    LOGIN_FAIL(-1,"登陆失败"),
    AUTH_ERROR(-1,"登录失败"),
    TOKEN_EXPIRE(-1,"Token过期"),
    TOKEN_FAIL(-1,"Token不存在"),
    SESSION_USER_ERROR(-1,"User未获取到");

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
