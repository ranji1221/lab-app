package com.ranji.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * 轮播图实体类
 * 代表可以上传到服务器图片资源
 * @RanJi
 * @RanJi
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Slide {
    @NonNull
    private int id;
    @NonNull
    private String name;
    @NonNull
    private String url;
}
