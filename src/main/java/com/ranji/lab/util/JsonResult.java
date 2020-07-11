package com.ranji.lab.util;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import java.io.Serializable;

/**
 * JsonResult工具类
 * @author RanJi
 */
@Data
public class JsonResult implements Serializable {
    private String status;
    private String result;

    public void setResult(Object result){
        this.result = JSON.toJSONString(result);
    }
}

