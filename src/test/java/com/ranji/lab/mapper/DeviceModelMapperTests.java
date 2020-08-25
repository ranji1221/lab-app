package com.ranji.lab.mapper;

import com.ranji.lab.entity.DeviceModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DeviceModelMapperTests {

    @Resource
    private DeviceModelMapper deviceModelMapper;
    @Test
    public void testifExistsThisDeviceModel(){
        List<DeviceModel> 精密ph试剂 = deviceModelMapper.ifExistsThisDeviceModel("精密ph试剂", "柯迪达CT-6021A");
        System.out.println(精密ph试剂.isEmpty());
    }
}
