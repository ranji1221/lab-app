package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * ClassName:    Role
 * Package:    com.ranji.lab.entity
 * Description: 角色实体类
 * Datetime:    2020/8/31   5:35 下午
 * Author:   ranji
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Role implements Serializable {
    private int id;
    @NonNull
    private String code;    //-- 角色英文名
    @NonNull
    private String name;    //-- 角色中文名

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}