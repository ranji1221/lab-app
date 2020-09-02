package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

/**
 * ClassName:    Right
 * Package:    com.ranji.lab.entity
 * Description: 代表系统中的权限，理论上说有多少接口，就对应多少个权限
 * 在系统部署之前，把所有接口对应的权限字符串提前初始化到数据库中即可
 * Datetime:    2020/9/1   2:13 下午
 * Author:   ranji
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Auth implements Serializable {
    private int id;

    @NonNull
    private String type;    //-- 权限的分类  例如：用户管理     这里最好为中文，该属性为前台展示做服务

    @NonNull
    private String name;    //-- 权限中文名字   例如：查询账户   该属性也是为前台展示做服务
    @NonNull
    private String code;    //-- 权限对应的字符串 例如：user:list

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auth auth = (Auth) o;
        return Objects.equals(code, auth.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}