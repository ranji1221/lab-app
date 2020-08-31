package com.ranji.lab.handler;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@RestControllerAdvice
public class MyExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MyExceptionHandler.class);

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
}
