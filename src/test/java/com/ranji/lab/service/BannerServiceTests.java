package com.ranji.lab.service;

import com.ranji.lab.entity.Images;
import com.ranji.lab.service.prototype.IBannerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class BannerServiceTests {

    @Resource
    private IBannerService iBannerService;

    @Test
    public void testInsertImageAndBanner(){
        Images images = new Images("aa","aa","aaa");
        iBannerService.insertOrUpdateBannerAndImages(1,images);
    }

}
