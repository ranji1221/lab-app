package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.*;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.News;
import org.apache.ibatis.annotations.Select;

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

    Map<Object,Object> findDeviceAndModel(int pageNum,int pageSize);

    int insertDeviceAndDeviceModel(DeviceAndModelDto deviceAndModelDto);

    int updateDeviceAndDeviceModel(DeviceAndModelDto deviceAndModelDto);

    Map<Object,Object> findIntelligentAnalyze(int pageNum,int pageSize);

    //没有分配实验室的设备的数量
    List<LaboratoryDeviceNumDto> findNoAllocationDeviceTypeNum();

    //按照实验室查询拥有设备数量
    List<LaboratoryDeviceNumDto> laboratoryIdFindDevice(int id);
}
