package com.ranji.lab.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * 文章缩略图实体类
 * 即每篇文章所对应的缩略图类
 */
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class ArticleThumbnail implements Serializable {
    private int id;
    private String title = "";
    @NonNull
    private String path; //-- 以Base64的形式保存图片到数据库
}
