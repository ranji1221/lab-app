package com.ranji.lab.mapper;

import com.ranji.lab.entity.News;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class NewsMapperTests {
    @Resource
    private NewsMapper newsMapper;

    @Test
    public void testAdd(){
        News news = new News("习主席访美","今天是个好日子，我们的胜利！");
        newsMapper.saveNews(news);
    }

    @Test
    public void testFind(){
        News news = newsMapper.findNews(1);
        System.out.println(news);
    }
}
