package com.ranji.lab.service.prototype;

import com.ranji.lab.entity.DeviceModel;

import java.util.List;

public interface IDeviceModelService {
    //插入
    int insertDeviceModel(DeviceModel deviceModel);
    //修改
    int updateDeviceModel(DeviceModel deviceModel);
    //查询全部
    List<DeviceModel> allDeviceModel();
    //按照类型查询
    List<DeviceModel> allDeviceModelByType(int type);
    //根据设备名字和型号判断是否存在
    List<DeviceModel> ifExistsThisDeviceModel(String deviceName,String brand);
    int findLatestDeviceModelId();
    int findDeviceModelIdByDeviceNameAndBrand(String deviceName,String brand);
}
