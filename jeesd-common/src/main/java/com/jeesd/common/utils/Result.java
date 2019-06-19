package com.jeesd.common.utils;

import java.io.Serializable;

public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String SUCCESS = "success";
    public static final String FAIL = "fail";
    public static final int SUCCESS_CODE = 0;
    public static final int FAIL_CODE = 1;

    private int code = SUCCESS_CODE;
    private String msg = SUCCESS;
    private T data;

    public Result() {
        super();
    }
    public Result(T data) {
        super();
        this.data = data;
    }
    public Result(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
