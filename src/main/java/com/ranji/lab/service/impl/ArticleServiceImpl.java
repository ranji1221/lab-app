package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Article;
import com.ranji.lab.entity.ArticleThumbnail;
import com.ranji.lab.mapper.ArticleMapper;
import com.ranji.lab.service.prototype.IArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * ClassName:    ArticleServiceImpl
 * Package:    com.ranji.lab.service.impl
 * Description:
 * Datetime:    2020/8/28   11:49 上午
 * Author:   ranji
 */
@Service
public class ArticleServiceImpl implements IArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public void issueArticle(Article article) {
        articleMapper.saveArticle(article);
    }

    @Override
    public Article getArticle(int id) {
        return articleMapper.findArticle(id);
    }

    @Override
    public int addArticleThumbnail(ArticleThumbnail articleThumbnail) {
        articleMapper.saveArticleThumbnail(articleThumbnail);
        return articleThumbnail.getId();
    }

    @Override
    public ArticleThumbnail getArticleThumbnail(int id) {
        return articleMapper.findArticleThumbnail(id);
    }
}