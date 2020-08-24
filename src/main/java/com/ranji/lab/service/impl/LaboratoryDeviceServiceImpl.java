package com.ranji.lab.service.impl;

import com.ranji.lab.dto.LaboratoryDto;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.entity.LaboratoryDevice;
import com.ranji.lab.mapper.LaboratoryDeviceMapper;
import com.ranji.lab.service.prototype.ILaboratoryDeviceService;
import com.ranji.lab.service.prototype.ILaboratoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaboratoryDeviceServiceImpl implements ILaboratoryDeviceService {

    @Resource
    private LaboratoryDeviceMapper laboratoryDeviceMapper;

    @Override
    public int insertLaboratoryDevice(LaboratoryDevice laboratoryDevice) {
        return laboratoryDeviceMapper.insertLaboratoryDevice(laboratoryDevice);
    }

    @Override
    public int updateLaboratoryDevice(LaboratoryDevice laboratoryDevice) {
        return laboratoryDeviceMapper.updateLaboratoryDevice(laboratoryDevice);
    }

    @Override
    public Map<Object, Object> allLaboratoryDevice() {
        List<LaboratoryDevice> laboratoryDevices = laboratoryDeviceMapper.allLaboratoryDevice();
        Map<Object,Object> laboratoryDevicesMap = new HashMap<>();
        laboratoryDevicesMap.put("data",laboratoryDevices);
        return laboratoryDevicesMap;
    }
}
