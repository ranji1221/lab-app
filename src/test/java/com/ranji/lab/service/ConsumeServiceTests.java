package com.ranji.lab.service;

import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.entity.Images;
import com.ranji.lab.mapper.ConsumeCustodyMapper;
import com.ranji.lab.service.prototype.IBannerService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ConsumeServiceTests {

    @Resource
    private ConsumeCustodyMapper consumeCustodyMapper;

    @Test
    public void testInsertImageAndBanner(){
        ConsumeCustodyDto nameById = consumeCustodyMapper.findNameById(1);

    }


}
