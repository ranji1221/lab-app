package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.NewsDto;
import com.ranji.lab.entity.News;

import java.util.List;
import java.util.Map;

/**
 * 新闻service接口
 */
public interface INewsService {

    int insertNews(NewsDto newsdto);

    int updateNews(News news);

    List<News> findAllNews();

    Map<Object,Object> findAllNews(int pageNum, int pageSize);

    News findById(int id);
}
