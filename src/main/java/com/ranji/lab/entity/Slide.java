package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 轮播图实体类
 * 代表可以上传到服务器图片资源
 * @RanJi
 * @RanJi
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Slide implements Serializable {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String url;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
