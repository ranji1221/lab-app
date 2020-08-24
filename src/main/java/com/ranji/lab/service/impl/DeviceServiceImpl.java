package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.DeviceAndDeviceTypeNameDto;
import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.entity.Device;
import com.ranji.lab.mapper.DeviceMapper;
import com.ranji.lab.service.prototype.IDeviceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Resource
    private DeviceMapper deviceMapper;
    @Override
    public int insertDevice(DeviceDto deviceDto) {
        return deviceMapper.insertDevice(deviceDto);
    }

    @Override
    public int updateDevice(Device device) {
        return deviceMapper.updateDevice(device);
    }

    @Override
    public List<Device> findAllDevice() {
        return deviceMapper.findAll();
    }

    @Override
    public Map<Object,Object> findAllDevice(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Device> allDevice = deviceMapper.findAll();

        PageInfo<Device> allDevicePaging = new PageInfo<>(allDevice);
        long total = allDevicePaging.getTotal();

        Map<Object,Object> allDeviceOnPaging = new HashMap<>();
        allDeviceOnPaging.put("data",allDevice);
        allDeviceOnPaging.put("total",total);
        return allDeviceOnPaging;
    }

    @Override
    public Device findById(int id) {
        return deviceMapper.findById(id);
    }

    @Override
    public Map<Object,Object> findAllDeviceByTypeId(int type) {
        List<DeviceDto> allDeviceByTypeId = deviceMapper.findAllDeviceByTypeId(type);
        Map<Object,Object> allDeviceByTypeIdMap = new HashMap<>();
        allDeviceByTypeIdMap.put("data",allDeviceByTypeId);
        return allDeviceByTypeIdMap;
    }

    @Override
    public List<DeviceAndDeviceTypeNameDto> findDeviceAndDeviceName() {
        return deviceMapper.findDeviceAndDeviceName();
    }
}
