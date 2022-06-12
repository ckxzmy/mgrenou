package com.example.mgdoll.model;


import com.example.mgdoll.conf.ApiResponseEnum;

/**
 * web层统一返回类型
 */
public class ApiResponse {
    private int code = 0;

    private String message;

    private Object data;

    public ApiResponse(){

    }


    public ApiResponse(Object data) {
        this.data = data;
    }

    public ApiResponse(ApiResponseEnum apiResponseEnum){
        this.code = apiResponseEnum.getErrCode();
        this.message = apiResponseEnum.getErrMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getmessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
