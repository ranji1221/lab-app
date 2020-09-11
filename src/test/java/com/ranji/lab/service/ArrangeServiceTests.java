package com.ranji.lab.service;

import com.ranji.lab.entity.Images;
import com.ranji.lab.mapper.BackStageDtoMapper;
import com.ranji.lab.service.prototype.IArrangeService;
import com.ranji.lab.service.prototype.IBannerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ArrangeServiceTests {

    @Resource
    private IArrangeService iArrangeService;
    @Resource
    private BackStageDtoMapper backStageDtoMapper;

    @Test
    public void testchangeArrangeStatus(){

        String nowTime = backStageDtoMapper.findNowTime();
        String nowDays = backStageDtoMapper.findNowDays(0);
        System.out.println(nowDays);
        System.out.println(nowTime);
    }

}
