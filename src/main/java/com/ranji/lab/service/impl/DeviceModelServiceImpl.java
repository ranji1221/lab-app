package com.ranji.lab.service.impl;

import com.ranji.lab.entity.DeviceModel;
import com.ranji.lab.mapper.DeviceModelMapper;
import com.ranji.lab.service.prototype.IDeviceModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceModelServiceImpl implements IDeviceModelService {

    @Resource
    DeviceModelMapper deviceModelMapper;
    @Override
    public int insertDeviceModel(DeviceModel deviceModel) {
        return deviceModelMapper.insertDeviceModel(deviceModel);
    }

    @Override
    public int updateDeviceModel(DeviceModel deviceModel) {
        return deviceModelMapper.updateDeviceModel(deviceModel);
    }

    @Override
    public List<DeviceModel> allDeviceModel() {
        return deviceModelMapper.allDeviceModel();
    }

    @Override
    public List<DeviceModel> allDeviceModelByType(int type) {
        return deviceModelMapper.allDeviceModelByType(type);
    }

    @Override
    public List<DeviceModel> ifExistsThisDeviceModel(String deviceName, String brand) {
        return deviceModelMapper.ifExistsThisDeviceModel(deviceName,brand);
    }

    @Override
    public int findLatestDeviceModelId() {
        return deviceModelMapper.findLatestDeviceModelId();
    }

    @Override
    public int findDeviceModelIdByDeviceNameAndBrand(String deviceName, String brand) {
        return deviceModelMapper.findDeviceModelIdByDeviceNameAndBrand(deviceName,brand);
    }
}
