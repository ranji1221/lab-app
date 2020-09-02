package com.ranji.lab.service;

import com.ranji.lab.dto.ReportRepairInsertDto;
import com.ranji.lab.dto.ScrapInsertDto;
import com.ranji.lab.entity.Scrap;
import com.ranji.lab.service.prototype.IReportRepairService;
import com.ranji.lab.service.prototype.IScrapService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class ReportRepairServiceTest {

    @Resource
    private IReportRepairService iReportRepairService;
    @Resource
    private IScrapService iScrapService;

    /*@Test
    public void testirrs(){
        ReportRepairInsertDto reportRepairInsertDto = new ReportRepairInsertDto();
        reportRepairInsertDto.setDate("2020-1-1");
        reportRepairInsertDto.setDescription("aaaaa");
        String[] a = {"1111","2222"};
        reportRepairInsertDto.setUuid(a);
        reportRepairInsertDto.setLaboratory("aaa");

        int i = iReportRepairService.insertReportRepair(reportRepairInsertDto);

    }*/
    /*@Test
    public void testiss(){
        ScrapInsertDto scrapInsertDto = new ScrapInsertDto();
        scrapInsertDto.setDate("2020-1-1");
        scrapInsertDto.setDescription("aaaaa");
        String[] a = {"1111","2222"};
        scrapInsertDto.setUuid(a);
        scrapInsertDto.setLaboratory("aaa");

        int i = iScrapService.insertScrap(scrapInsertDto);

    }*/
}
