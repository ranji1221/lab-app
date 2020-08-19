package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.News;
import com.ranji.lab.mapper.NewsMapper;
import com.ranji.lab.service.prototype.INewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl implements INewsService {

    @Resource
    private NewsMapper newsMapper;
    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public List<News> findAllNews() {
        return newsMapper.findAll();
    }

    @Override
    public Map<Object,Object> findAllNews(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<News> allNews = newsMapper.findAll();

        PageInfo<News> allNewsPaging = new PageInfo<>(allNews);
        long total = allNewsPaging.getTotal();
        
        Map<Object,Object> allNewsOnPaging = new HashMap<>();
        allNewsOnPaging.put("data",allNews);
        allNewsOnPaging.put("total",total);
        return allNewsOnPaging;
    }

    @Override
    public News findById(int id) {
        return newsMapper.findById(id);
    }

    @Override
    public Map<Object, Object> findNewsNextToNext(int newsId) {
        List<News> newsNextToNext = newsMapper.findNewsNextToNext(newsId);
        Map<Object,Object> newsNextToNextMap = new HashMap<>();
        newsNextToNextMap.put("data",newsNextToNext);
        return newsNextToNextMap;
    }
}
