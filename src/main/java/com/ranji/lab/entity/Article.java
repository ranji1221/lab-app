package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文章实体类
 */
@Data
@NoArgsConstructor
public class Article implements Serializable {
    private int id;
    @NonNull
    private String title;           //-- 标题
    @NonNull
    private String summary;         //-- 摘要
    @NonNull
    private String thumbnailUrl;    //-- 缩略图路径（可访问的url位置）
    @NonNull
    private String content;         //-- 内容
    @NonNull
    private String publisher;       //-- 发布者

    private Date createTime;    //--   创建时间
    private Date lastTime;      //--   最好一次更新时间

    public Article(@NonNull String title, @NonNull String summary, @NonNull String thumbnailUrl, @NonNull String content, @NonNull String publisher) {
        this.title = title;
        this.summary = summary;
        this.thumbnailUrl = thumbnailUrl;
        this.content = content;
        this.publisher = publisher;

        Date now = new Date();
        this.createTime = now;
        this.lastTime = now;
    }

    //-- 设置时间的方法，格式为：2020-08-20 11:13:45
    public void setCreateTime(String createTime)  {
        try {
            this.createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    //-- 设置最后一次更新时间
    public void setLastTime(String lastTime) {
        try {
            this.lastTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(lastTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ;
    }

    public String getCreateTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(createTime);
    }

    public String getLastTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(lastTime);
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
