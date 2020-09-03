package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.NewsDto;
import com.ranji.lab.entity.News;

import java.util.List;
import java.util.Map;

/**
 * 新闻service接口
 */
public interface INewsService {

    int insertNews(NewsDto newsDto);

    int updateNews(News news);

    Map<Object,Object> findAllNews();

    Map<Object,Object> findAllNews(int pageNum, int pageSize);

    News findById(int id);

    Map<Object,Object> findNewsNextToNext(int newsId);
    //模糊查找
    Map<Object,Object> findLikeNews(String like,int pageNum, int pageSize);
    public Map<Object, Object> findLikeNews(String like);
}
