package com.ranji.lab.service;

import com.ranji.lab.entity.Article;
import com.ranji.lab.service.prototype.IArticleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ClassName:    ArticleServiceTests
 * Package:    com.ranji.lab.service
 * Description:
 * Datetime:    2020/8/28   11:51 上午
 * Author:   ranji
 */
@SpringBootTest
public class ArticleServiceTests {
    @Resource
    private IArticleService articleService;

    @Test
    public void addArticle(){
        Article article = new Article("Hello,China","这是个励志的故事","http://localhost:8080/lab/articlethum/1","我是一个很渺小的人，人生只是一场修行而已！","jiran");
        articleService.issueArticle(article);
    }

    @Test
    public void getArticle(){
        Article article = articleService.getArticle(1);
        System.out.println(article);
    }
}