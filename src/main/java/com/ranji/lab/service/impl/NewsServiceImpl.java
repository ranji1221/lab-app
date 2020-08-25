package com.ranji.lab.service.impl;

import com.ranji.lab.entity.News;
import com.ranji.lab.entity.NewsImage;
import com.ranji.lab.mapper.NewsMapper;
import com.ranji.lab.service.prototype.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 新闻业务实现类
 */
@Service
public class NewsServiceImpl implements INewsService {
    @Resource
    private NewsMapper newsMapper;

    @Override
    public void issueNews(News news) {
        newsMapper.saveNews(news);
    }

    @Override
    public News getNews(int id) {
        return newsMapper.findNews(id);
    }

    @Override
    public int addNewsImage(NewsImage newsImage) {
        newsMapper.saveNewsImage(newsImage);
        return newsImage.getId();
    }

    @Override
    public NewsImage getNewsImage(int id) {
        return newsMapper.findNewsImage(id);
    }
}
