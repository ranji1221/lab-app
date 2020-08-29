package com.ranji.lab.handler;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName:    ExceptionHanlder
 * Package:    com.ranji.lab.handler
 * Description: 全局异常处理类
 * Datetime:    2020-8-29  10:56
 * Author:   RanJi
 */
@RestControllerAdvice
public class ExceptionHanlder {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHanlder.class);

    /**
     * 处理全局异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object handleException(Exception e, HttpServletRequest request){
        //-- 1. 日志输出
        LOG.info("url {}, msg {}",request.getRequestURL(),e.getMessage());
        //-- 2. 返回JSON对象，看前端怎么处理即可
        JSONObject jo = new JSONObject();
        jo.put("code",100);
        jo.put("msg",e.getMessage());
        jo.put("url",request.getRequestURL());
        return jo;
    }

    /**
     * 处理自定义异常
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = MyException.class)
    public Object handleMyException(MyException e, HttpServletRequest request){
        //-- 1. 日志输出
        LOG.info("url {}, msg {}",request.getRequestURL(),e.getMessage());
        //-- 2. 返回JSON对象，看前端怎么处理即可
        JSONObject jo = new JSONObject();
        jo.put("code",e.getCode());
        jo.put("msg",e.getMsg());
        jo.put("url",request.getRequestURL());
        return jo;
    }

}
