package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Images;
import com.ranji.lab.mapper.ImageMapper;
import com.ranji.lab.service.prototype.IImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ImageServiceImpl implements IImageService {
    @Resource
    private ImageMapper imageMapper;

    @Override
    public int insertImage(Images images) {
        return imageMapper.insertImage(images);
    }

    @Override
    public Images findImagesById(int id) {
        return imageMapper.findImagesById(id);
    }
}
