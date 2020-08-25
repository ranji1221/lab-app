package com.ranji.lab.service;

import com.ranji.lab.dto.DeviceAndModelDto;
import com.ranji.lab.service.prototype.IDeviceService;
import com.ranji.lab.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Date;

@SpringBootTest
public class DeviceModelSetviceTests {

    @Resource
    private IDeviceService iDeviceService;

    @Test
    public void testInsertDeviceAndDeviceModel(){

        String factime = DateUtil.DateToString(DateUtil.StringToDate("2020-02-02","yyyy-MM-dd"),"yyyy-MM-dd");
        String deviceName = "精密ph试剂1";
        String brand = "柯迪达CT-6021A";
        String facid = "111";
        String proid = "111";
        String supid = "111";
        String type = "1";
        String unitName = "个";
        int lifetime = 100;
        int count = 1;
        DeviceAndModelDto deviceAndModelDto = new DeviceAndModelDto(factime,deviceName,brand,facid,proid,supid,type,unitName,lifetime,count);
        int i = iDeviceService.insertDeviceAndDeviceModel(deviceAndModelDto);
        System.out.println(i);
    }
}
