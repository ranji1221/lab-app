package com.ranji.lab.service.impl;

import com.ranji.lab.entity.NewsImage;
import com.ranji.lab.mapper.NewsImageMapper;
import com.ranji.lab.service.prototype.INewsImageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class NewsImageServiceImpl implements INewsImageService {

    @Resource
    private NewsImageMapper newsImageMapper;

    @Override
    public int insertNewsImage(NewsImage newsImage) {
        newsImageMapper.insertNewsImage(newsImage);
        return newsImage.getId();
    }

    @Override
    public NewsImage findNewsImageById(int id) {
        return newsImageMapper.findNewsImageById(id);
    }
}
