package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.DeviceAndDeviceTypeNameDto;
import com.ranji.lab.dto.DeviceDto;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.News;

import java.util.List;
import java.util.Map;

public interface IDeviceService {

    int insertDevice(Device device);

    int updateDevice(Device device);

    List<Device> findAllDevice();

    Map<Object,Object> findAllDevice(int pageNum, int pageSize);

    Device findById(int id);

    Map<Object,Object> findAllDeviceByTypeId(int type);

    List<DeviceAndDeviceTypeNameDto> findDeviceAndDeviceName();
}
