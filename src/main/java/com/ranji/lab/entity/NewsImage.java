package com.ranji.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 新闻所对应的图片实体类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class NewsImage implements Serializable {
    private int id;
    private String title;
    @NonNull
    private String url; //-- 保存图片的路径
}
