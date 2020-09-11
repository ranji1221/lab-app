package com.ranji.lab.handler;

/*
 * ClassName:    MyException
 * Package:    com.ranji.lab.handler
 * Description: 自定义异常类
 * Datetime:    2020-8-29  11:18
 * Author:   RanJi
 */

public class MyException extends RuntimeException {
    private String code;
    private String msg;

    MyException(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
