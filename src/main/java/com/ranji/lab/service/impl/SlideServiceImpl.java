package com.ranji.lab.service.impl;

import com.ranji.lab.entity.Slide;
import com.ranji.lab.mapper.SlideMapper;
import com.ranji.lab.service.prototype.ISlideService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 轮播图业务实现类
 */
@Service
public class SlideServiceImpl implements ISlideService {

    @Resource
    private SlideMapper slideMapper;

    @Override
    public void saveOrUpdateSlide(Slide slide) {
        Slide s = slideMapper.findByID(slide.getId());
        if(s==null){
            slideMapper.save(slide);
        }else{
            slideMapper.update(slide);
        }
    }

    @Override
    public Slide getSlide(int id) {
        return slideMapper.findByID(id);
    }
}
