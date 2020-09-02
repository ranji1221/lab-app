package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.*;
import java.io.Serializable;

/**
 * 用户类
 * 利用lombok框架自动生成基础代码，下面是对lombok标注的说明：
 * @Data
 *  自动生成getter、setter、toString、equals、hashCode方法
 * @NoArgsConstructor
 *  自动生成缺省构造器
 * @RequiredArgsConstructor
 *  和@NonNull配置生成适合参数的构造器
 * @author RanJi
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User implements Serializable {
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String password;
    @NonNull
    private int enable;    //-- 1: 启用  2: 禁用

    /**
     * 重写toString方法
     */
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
