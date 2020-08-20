package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.entity.Device;
import com.ranji.lab.service.prototype.IDeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Resource
    private IDeviceService iDeviceService;
    @Override
    public int insertDevice(Device device) {
        return iDeviceService.insertDevice(device);
    }

    @Override
    public int updateDevice(Device device) {
        return iDeviceService.updateDevice(device);
    }

    @Override
    public List<Device> findAllDevice() {
        return iDeviceService.findAllDevice();
    }

    @Override
    public Map<Object,Object> findAllDevice(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Device> allDevice = iDeviceService.findAllDevice();

        PageInfo<Device> allDevicePaging = new PageInfo<>(allDevice);
        long total = allDevicePaging.getTotal();

        Map<Object,Object> allDeviceOnPaging = new HashMap<>();
        allDeviceOnPaging.put("data",allDevice);
        allDeviceOnPaging.put("total",total);
        return allDeviceOnPaging;
    }

    @Override
    public Device findById(int id) {
        return iDeviceService.findById(id);
    }
}
