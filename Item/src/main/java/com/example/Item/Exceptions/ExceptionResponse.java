package com.example.Item.Exceptions;

import org.springframework.http.HttpStatus;

public class ExceptionResponse {
    private int code;
    private String msg;
    private HttpStatus status;

    public ExceptionResponse()
    {

    }
    public ExceptionResponse(int code, String msg, HttpStatus status) {
        super();
        this.code = code;
        this.msg = msg;
        this.status = status;
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}


