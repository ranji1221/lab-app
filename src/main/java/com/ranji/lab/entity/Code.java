package com.ranji.lab.entity;

import java.io.Serializable;

/**
 * 代码状态枚举
 */
public enum Code implements Serializable {
    SUCCESS("code",200),
    FAILURE("code",600);

    private String msg;
    private int code;

    private Code(String msg,int code){
        this.msg = msg;
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code = code;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
}
