package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.DeviceTypeDto;
import com.ranji.lab.entity.DeviceType;

import java.util.Map;

public interface IDeviceTypeService {
    int insertDeviceType(DeviceTypeDto deviceTypeDto);

    int updateDeviceType(DeviceType deviceType);

    Map<Object,Object> allDeviceType();


    Map<Object,Object> allDeviceTypePaging(int pageNum,int pageSize);


}
