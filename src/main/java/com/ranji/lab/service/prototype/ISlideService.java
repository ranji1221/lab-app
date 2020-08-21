package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.Slide;

/**
 * 轮播图业务接口
 * @RanJi
 */
public interface ISlideService {
    /**
     * 保存或者更新轮播图
     * @param slide
     */
    void saveOrUpdateSlide(Slide slide);

    /**
     * 获取某张轮播图
     * @param id
     * @return
     */
    Slide getSlide(int id);

}
