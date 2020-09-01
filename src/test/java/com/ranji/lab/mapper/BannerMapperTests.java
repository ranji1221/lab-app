package com.ranji.lab.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BannerMapperTests {
    @Resource
    private BannerMapper bannerMapper;
    @Resource
    private ImageMapper imageMapper;

    @Test
    public void testLatestImage(){
        int i = imageMapper.latestImageData();
        System.out.println(i);
    }

}
