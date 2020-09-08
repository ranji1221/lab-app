package com.ranji.lab.mapper;

import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.service.prototype.IBackStageDtoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class BackStageDtoMapperTests {
    @Resource
    private BackStageDtoMapper backStageDtoMapper;

    @Resource
    private IBackStageDtoService iBackStageDtoService;


    @Test
    public void findAllArrange(){
        String nowAndLatestSevenDays = backStageDtoMapper.findNowDays(1);
        System.out.println(nowAndLatestSevenDays);

    }
    @Test
    public void findAllArrange2(){
        Map<Object, Object> allMap = iBackStageDtoService.findNowAndLatestSevenDaysData();
        System.out.println(allMap);
    }
}
