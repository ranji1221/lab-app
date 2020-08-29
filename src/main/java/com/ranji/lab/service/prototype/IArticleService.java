package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Article;
import com.ranji.lab.entity.ArticleThumbnail;

/**
 * ClassName:    IArticleService
 * Package:    com.ranji.lab.service.prototype
 * Description: 文章业务接口
 * Datetime:    2020/8/28   11:32 上午
 * Author:   ranji
 */
public interface IArticleService {
    //-- 发布新闻
    void issueArticle(Article article);
    //-- 根据获取某篇文章
    Article getArticle(int id);
    //-- 添加文章缩略图(返回保存图片的ID)
    int addArticleThumbnail(ArticleThumbnail articleThumbnail);
    //-- 根据缩微图ID获取缩略图对象 (关键是要获取到缩略图对象里的图片存储的路径)
    ArticleThumbnail getArticleThumbnail(int id);
}