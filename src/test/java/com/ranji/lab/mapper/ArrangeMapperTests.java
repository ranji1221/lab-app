package com.ranji.lab.mapper;

import com.ranji.lab.dto.ArrangeDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class ArrangeMapperTests {
    @Resource
    private ArrangeMapper arrangeMapper;

    @Test
    public void testLatestImage(){
        List<ArrangeDto> arrangeDtos = arrangeMapper.yesOrNoArrange(1, "2020-8-20", "11:00:00", "15:00:00");
        for (ArrangeDto arrangeDto : arrangeDtos) {
            System.out.println(arrangeDto);
        }

    }
}
