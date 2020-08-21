package com.ranji.lab.mapper;

import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.entity.Arrange;
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
        Arrange arrange = new Arrange();
        arrange.setLaboratoryId(1);
        arrange.setDate("2020-8-20");
        arrange.setTimeStart("14:00");
        arrange.setTimeStop("16:00");
        List<ArrangeDto> arrangeDtos = arrangeMapper.yesOrNoArrange(arrange);
        for (ArrangeDto arrangeDto : arrangeDtos) {
            System.out.println(arrangeDto);
        }

    }
    @Test
    public void findAllArrange(){
        List<ArrangeDto> arrangeDtos = arrangeMapper.findAllArrange(1);

    }
}
