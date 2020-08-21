package com.ranji.lab.service;

import com.ranji.lab.entity.Slide;
import com.ranji.lab.service.prototype.ISlideService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

/**
 * 资源服务测试
 * @RanJi
 */
@SpringBootTest
public class SlideServiceTests {
    @Resource
    private ISlideService slideService;

    @Test
    public void testAdd(){
        //-- 1. 必须指定slide的ID属性
        Slide slide = new Slide(1,"aaa","/aaa");
        slideService.saveOrUpdateSlide(slide);
    }

    @Test
    public void testFind(){
        Slide slide = slideService.getSlide(1);
        System.out.println(slide);
    }

    @Test
    public void testUpdate(){
        Slide slide = new Slide(1,"bbb","/bbb");
        slideService.saveOrUpdateSlide(slide);
    }
}
