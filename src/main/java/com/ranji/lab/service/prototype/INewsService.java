package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.News;
import com.ranji.lab.entity.NewsImage;

/**
 * 新闻服务类
 */
public interface INewsService {
    //-- 发布新闻
    void issueNews(News news);
    //-- 获取1篇新闻
    News getNews(int id);
    //-- 添加新闻图片(返回保存图片的ID)
    int addNewsImage(NewsImage newsImage);
    //-- 获取新闻图片
    NewsImage getNewsImage(int id);
}
