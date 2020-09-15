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


    /*@Test
    public void findAllArrange(){
        String nowAndLatestSevenDays = backStageDtoMapper.findNowDays(0);
        System.out.println(nowAndLatestSevenDays);
        int noCount = backStageDtoMapper.findNoCount(nowAndLatestSevenDays);
        int finishedCount = backStageDtoMapper.findFinishedCount(nowAndLatestSevenDays);
        int unfinishedCount = backStageDtoMapper.findUnfinishedCount(nowAndLatestSevenDays);
        int allCount = backStageDtoMapper.findAllCount();
        System.out.println("no"+noCount);
        System.out.println("no"+finishedCount);
        System.out.println("no"+unfinishedCount);
        System.out.println("no"+allCount);

    }*/
    /*@Test
    public void findAllArrange2(){
        Map<Object, Object> allMap = iBackStageDtoService.findNowAndLatestSevenDaysData();
        System.out.println(allMap);
    }*/
}
