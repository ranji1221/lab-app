package com.ranji.lab.service;

import com.ranji.lab.entity.Device;
import com.ranji.lab.mapper.DeviceMapper;
import com.ranji.lab.service.prototype.IDeviceService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class DeviceServiceTest {
    @Resource
    DeviceMapper deviceMapper;
    @Resource
    IDeviceService iDeviceService;

    @Test
    public void test(){
        List<Device> all = deviceMapper.findAll();
        System.out.println(all);
    }
    @Test
    public void testservice(){
        System.out.println(iDeviceService.findAllDevice());
    }
}
