package com.ranji.lab.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.DeviceModel;
import com.ranji.lab.mapper.DeviceMapper;
import com.ranji.lab.mapper.DeviceModelMapper;
import com.ranji.lab.service.prototype.IDeviceService;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class DeviceServiceImpl implements IDeviceService {
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private DeviceModelMapper deviceModelMapper;
    @Override
    public int insertDevice(Device device) {

        return deviceMapper.insertDevice(device);
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

    @Override
    public Map<Object,Object> findDeviceAndModel(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<DeviceAndDeviceTypeNameDto> deviceAndDeviceName = deviceMapper.findDeviceAndDeviceName();

        PageInfo deviceAndDeviceNamePaging = new PageInfo(deviceAndDeviceName);
        long total = deviceAndDeviceNamePaging.getTotal();

        Map<Object,Object> deviceAndDeviceNameMap = new HashMap<>();
        deviceAndDeviceNameMap.put("total",total);
        deviceAndDeviceNameMap.put("data",deviceAndDeviceName);

        return deviceAndDeviceNameMap;
    }

    @Override
    @Transactional
    public int insertDeviceAndDeviceModel(DeviceAndModelDto deviceAndModelDto) {
        String deviceName = deviceAndModelDto.getDeviceName();
        String brand = deviceAndModelDto.getBrand();
        List<DeviceModel> deviceModels = deviceModelMapper.ifExistsThisDeviceModel(deviceName, brand);
        //健壮性判断（如果是空的说明没有该项DeviceModel）
        if(deviceModels.isEmpty()){
            DeviceModel deviceModel = new DeviceModel();
            deviceModel.setBrand(deviceAndModelDto.getBrand());
            deviceModel.setCount(deviceAndModelDto.getCount());
            deviceModel.setDeviceName(deviceAndModelDto.getDeviceName());
            deviceModel.setFacid(deviceAndModelDto.getFacid());
            deviceModel.setLifetime(deviceAndModelDto.getLifetime());
            deviceModel.setProid(deviceAndModelDto.getProid());
            deviceModel.setSupid(deviceAndModelDto.getSupid());
            deviceModel.setUnitName(deviceAndModelDto.getUnitName());
            deviceModel.setType(deviceAndModelDto.getType());
            //插入到DeviceModel表
            int i = deviceModelMapper.insertDeviceModel(deviceModel);
            //健壮性判断
            if(i<0) return 0;
            //成功创建新的device_model
            else{
                //获得到最新的device_model_id
                int deviceModelId = deviceModelMapper.findLatestDeviceModelId();
                //开始通过设备数量创建插入device
                int count = deviceAndModelDto.getCount();
                //健壮性判断
                if(count < 0) return 0;
                else{
                    for (int i1 = 0;i1<count;i1++){
                        Device device = new Device();
                        device.setDeviceModelId(deviceModelId);
                        device.setFactime(deviceAndModelDto.getFactime());
                        device.setUuid(UUID.randomUUID().toString());
                        int i2 = deviceMapper.insertDevice(device);
                        if(i2<0) return 0;
                    }
                    return 1;
                }
            }
        }else {
            //这种情况就是存在该项deviceModel
            //获得通过设备名字和品牌找到的DeviceModel对象更改count
            DeviceModel deviceModel = deviceModels.get(0);
            int count = deviceAndModelDto.getCount();
            deviceModel.setCount(count);
            //更新DeviceModel中的总数
            int i = deviceModelMapper.updateDeviceModel(deviceModel);
            //通过名字和品牌找到DeviceModel中的DeviceModelId
            int DeviceModelId = deviceModelMapper.findDeviceModelIdByDeviceNameAndBrand(deviceName, brand);

            if(i<1){
                return 0;
            }else{
                for(int i1=0;i1<count;i1++){
                    Device device = new Device();
                    device.setDeviceModelId(DeviceModelId);
                    device.setFactime(deviceAndModelDto.getFactime());
                    device.setUuid(UUID.randomUUID().toString());
                    int i2 = deviceMapper.insertDevice(device);
                    if(i2<0) return 0;
                }
                return 1;
            }
        }
    }

    @Override
    public int updateDeviceAndDeviceModel(DeviceAndModelDto deviceAndModelDto) {
        DeviceModel deviceModel = new DeviceModel();
        deviceModel.setId(deviceAndModelDto.getId());
        deviceModel.setBrand(deviceAndModelDto.getBrand());
        deviceModel.setCount(deviceAndModelDto.getCount());
        deviceModel.setDeviceName(deviceAndModelDto.getDeviceName());
        deviceModel.setFacid(deviceAndModelDto.getFacid());
        deviceModel.setLifetime(deviceAndModelDto.getLifetime());
        deviceModel.setProid(deviceAndModelDto.getProid());
        deviceModel.setSupid(deviceAndModelDto.getSupid());
        deviceModel.setUnitName(deviceAndModelDto.getUnitName());
        deviceModel.setType(deviceAndModelDto.getType());
        int i = deviceModelMapper.updateDeviceModel(deviceModel);
        if(i<1) return 0;
        else{
            Device device = new Device();
            device.setUuid(UUID.randomUUID().toString());
            device.setFactime(deviceAndModelDto.getFactime());
            //根据名字和品牌查询deviceModelId
            int deviceId = deviceModelMapper.findDeviceModelIdByDeviceNameAndBrand(deviceAndModelDto.getDeviceName(),deviceAndModelDto.getBrand());
            device.setDeviceModelId(deviceId);
            int i1 = deviceMapper.updateDevice(device);
            if(i1<1) return 0;
            return 1;
        }

    }

    @Override
    public Map<Object, Object> findIntelligentAnalyze(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<DeviceIntelligentAnalyzeDto> intelligentAnalyze = deviceMapper.findIntelligentAnalyze();

        PageInfo deviceAndDeviceNamePaging = new PageInfo(intelligentAnalyze);
        long total = deviceAndDeviceNamePaging.getTotal();

        Map<Object,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",intelligentAnalyze);

        return map;
    }

    @Override
    public Map<Object, Object> findAllIntelligentAnalyze() {
        List<DeviceIntelligentAnalyzeDto> intelligentAnalyze = deviceMapper.findIntelligentAnalyze();
        Map<Object,Object> map = new HashMap<>();
        map.put("data",intelligentAnalyze);
        return map;
    }

    @Override
    public List<LaboratoryDeviceNumDto> findNoAllocationDeviceTypeNum() {
        return deviceMapper.findNoAllocationDeviceTypeNum();
    }

    @Override
    public List<LaboratoryDeviceNumDto> laboratoryIdFindDevice(int id, Integer status) {
        return deviceMapper.laboratoryIdFindDevice(id, status);
    }

    //按照实验室id查询设备信息、数量及设备状态
    @Override
    public List<LaboratoryDeviceNumDto> laboratoryIdFindDeviceAndStatus(int laboratoryId) {
        return deviceMapper.laboratoryIdFindDeviceAndStatus(laboratoryId);
    }

    //按照实验室id查询所有的设备
    @Override
    public List<DeviceAndDeviceTypeNameDto> laboratoryIdFindAllDevice(int laboratoryId) {
        return deviceMapper.laboratoryIdFindAllDevice(laboratoryId);
    }

    //按照设备状态、设备分类和实验室id查询数据
    @Override
    public Map<Object, Object> findDeviceStatusNum(int laboratoryId) {
        List<Integer> deviceModel = deviceMapper.findDeviceModel(laboratoryId);
        List<LaboratoryDeviceNumDto> laboratoryDeviceNum= new ArrayList<>();
        for (Integer integer : deviceModel) {
            LaboratoryDeviceNumDto laboratoryDeviceNumDto = new LaboratoryDeviceNumDto();
            LaboratoryDeviceNumDto deviceStatusNum = deviceMapper.findDeviceStatusNum(laboratoryId, integer, 0);
            LaboratoryDeviceNumDto deviceStatusNum1 = deviceMapper.findDeviceStatusNum(laboratoryId, integer, 1);
            LaboratoryDeviceNumDto deviceStatusNum2 = deviceMapper.findDeviceStatusNum(laboratoryId, integer, 2);

            laboratoryDeviceNumDto.setDeviceName(deviceStatusNum.getDeviceName());
            laboratoryDeviceNumDto.setId(deviceStatusNum.getId());
            laboratoryDeviceNumDto.setStatus(deviceStatusNum.getStatus());
            laboratoryDeviceNumDto.setCount(deviceStatusNum.getCount());
            laboratoryDeviceNumDto.setCount1(deviceStatusNum1.getCount());
            laboratoryDeviceNumDto.setCount2(deviceStatusNum2.getCount());
            laboratoryDeviceNum.add(laboratoryDeviceNumDto);
        }
        Map<Object,Object> map = new HashMap<>();
        map.put("data",laboratoryDeviceNum);
        return map;
    }

    //查询设备使用率
    @Override
    public Map<Object, Object> findUsageRate() {
        //按照实验室id
        //List<DeviceAndDeviceTypeNameDto> devices = deviceMapper.laboratoryIdFindAllDevice(laboratoryId);
        //查询全部
        List<DeviceAndDeviceTypeNameDto> devices = deviceMapper.findAllDevice();
        List<DeviceAndDeviceTypeNameDto> UsageRate = new ArrayList<>();
        for (DeviceAndDeviceTypeNameDto device : devices) {
            double endingProjectNum = deviceMapper.findEndingProjectNumByLaboratoryId(device.getLaboratoryId());
            double endingDeviceNum = deviceMapper.findEndingProjectNumByLaboratoryIdAndDeviceId(device.getLaboratoryId(), device.getId());
            if (endingProjectNum != 0 && endingDeviceNum != 0) {
                device.setUsageRate(endingDeviceNum / endingProjectNum);
            } else {
                device.setUsageRate(0);
            }
            UsageRate.add(device);
        }
        Map<Object,Object> map = new HashMap<>();
        map.put("data",UsageRate);
        return map;
    }

    //查询设备的损耗率
    @Override
    public Map<Object, Object> findRatio() {
        //按照实验室id
        //List<DeviceAndDeviceTypeNameDto> devices = deviceMapper.laboratoryIdFindAllDevice(laboratoryId);
        //查询全部
        List<DeviceAndDeviceTypeNameDto> devices = deviceMapper.findAllDevice();
        List<DeviceAndDeviceTypeNameDto> r = new ArrayList<>();
        for (DeviceAndDeviceTypeNameDto device : devices) {
            double i = deviceMapper.deviceIdFindUseNum(device.getId()) * 2;
            double x = device.getLifetime();
            if (i != 0 && x != 0) {
                double ratio = i / x;
                device.setRatio(ratio);
            }
            r.add(device);
        }
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data",r);
        return map;
    }

    @Override
    public List<Device> findDeviceByuuid(String uuid) {
        List<Device> deviceByuuid = deviceMapper.findDeviceByuuid(uuid);
        return deviceByuuid;
    }

    /**
     * 模糊查询
     *
     * @param like
     * @return
     */
    @Override
    public Map<Object, Object> likeFindDeviceAndDeviceName(int pageNum, int pageSize, String like) {
        PageHelper.startPage(pageNum, pageSize);
        List<DeviceAndDeviceTypeNameDto> deviceAndDeviceTypeNameDtos = deviceMapper.likeFindDeviceAndDeviceName(like);
        HashMap<Object, Object> allMap = new HashMap<>();
        PageInfo pageInfo = new PageInfo(deviceAndDeviceTypeNameDtos);
        long total = pageInfo.getTotal();
        allMap.put("data", deviceAndDeviceTypeNameDtos);
        allMap.put("total", total);
        return allMap;
    }

    @Override
    public Map<Object, Object> findStatusAndSum() {
        List<BackStage3Dto> all = deviceMapper.findStatusAndSum();
        HashMap<Object, Object> map = new HashMap<>();
        map.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        map.put("data", all);
        return map;
    }


}
