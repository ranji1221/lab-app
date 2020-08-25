package com.ranji.lab.entity;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 新闻实体
 */
@Data
@NoArgsConstructor
public class News implements Serializable {
    private int id;
    @NonNull
    private String title;
    @NonNull
    private String content;

    private Date createTime;    //--   创建时间
    private Date lastTime;      //--   最好一次更新时间

    //-- 创建新闻默认是当前时间
    public News(String title,String content){
        this.title = title;
        this.content = content;
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
