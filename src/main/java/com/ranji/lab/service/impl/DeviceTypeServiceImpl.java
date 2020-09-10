package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.DeviceTypeDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.DeviceType;
import com.ranji.lab.mapper.DeviceTypeMapper;
import com.ranji.lab.service.prototype.IDeviceTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DeviceTypeServiceImpl implements IDeviceTypeService {
    @Resource
    private DeviceTypeMapper deviceTypeMapper;


    @Override
    public int insertDeviceType(DeviceTypeDto deviceTypeDto) {
        return deviceTypeMapper.insertDeviceType(deviceTypeDto);
    }

    @Override
    public int updateDeviceType(DeviceType deviceType) {
        return deviceTypeMapper.updateDeviceType(deviceType);
    }

    @Override
    public Map<Object, Object> allDeviceType() {
        List<DeviceType> deviceTypes = deviceTypeMapper.allDeviceType();
        Map<Object,Object> deviceTypesMap = new HashMap<>();
        deviceTypesMap.put("data",deviceTypes);
        return deviceTypesMap;
    }

    @Override
    public Map<Object, Object> allDeviceTypePaging(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DeviceType> all = deviceTypeMapper.allDeviceType();

        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();
        HashMap<Object, Object> allMap = new HashMap<>();
        allMap.put("data", all);
        allMap.put("total", total);
        allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        return allMap;
    }
}
