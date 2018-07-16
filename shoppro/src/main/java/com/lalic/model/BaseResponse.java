package com.lalic.model;

public class BaseResponse {

    private int code=200;

    private String mess="ok";

    private Object data;

    public Object getData() {
        return data;
    }

    public BaseResponse setData(Object data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public BaseResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMess() {
        return mess;
    }

    public BaseResponse setMess(String mess) {
        this.mess = mess;
        return this;
    }
}
