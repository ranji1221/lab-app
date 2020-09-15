package com.ranji.lab.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.Laboratory;
import com.ranji.lab.entity.LaboratoryDevice;
import com.ranji.lab.mapper.DeviceMapper;
import com.ranji.lab.mapper.LaboratoryDeviceMapper;
import com.ranji.lab.mapper.LaboratoryMapper;
import com.ranji.lab.service.prototype.ILaboratoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LaboratoryServiceImpl implements ILaboratoryService {

    @Resource
    private LaboratoryMapper laboratoryMapper;
    @Resource
    private LaboratoryDeviceMapper laboratoryDeviceMapper;
    @Resource
    private DeviceMapper deviceMapper;

    @Override
    @Transactional
    public int insertLaboratory(LaboratoryDto laboratoryDto, String devices) {
        int i = 0;
        List<LaboratoryDeviceDto> device = JSON.parseObject(devices, new TypeReference<ArrayList<LaboratoryDeviceDto>>() {
        });
        for (LaboratoryDeviceDto laboratoryDeviceDto : device) {
            List<Device> noAllocationDevice = deviceMapper.findNoAllocationDevice(laboratoryDeviceDto.getExperimentDeviceId(), laboratoryDeviceDto.getDeviceNum());
            if (noAllocationDevice.size() < laboratoryDeviceDto.getDeviceNum()) {
                return 0;
            }
            if (laboratoryDto.getId() != 0) {
                i = laboratoryMapper.insertLaboratory(laboratoryDto);
            }
            for (Device device1 : noAllocationDevice) {
                LaboratoryDevice laboratoryDevice = new LaboratoryDevice();
                laboratoryDevice.setDeviceId(device1.getId());
                laboratoryDevice.setLaboratoryId(laboratoryDto.getId());
                laboratoryDevice.setStatus(0);
                laboratoryDeviceMapper.insertLaboratoryDevice(laboratoryDevice);
            }
        }
        return i;
    }

    @Override
    public int updateLaboratory(Laboratory laboratory) {
        return laboratoryMapper.updateLaborytory(laboratory);
    }

    @Override
    public Map<Object,Object> findAllLaboratory() {
        List<Laboratory> all = laboratoryMapper.findAll();
        Map<Object,Object> allLaboratory = new HashMap<>();
        allLaboratory.put("data",all);
        return allLaboratory;
    }


    @Override
    public List<Laboratory> dateFindAll(String date, String timeStart, String timeStop) {
        return laboratoryMapper.dateFindAll(date,timeStart,timeStop);
    }

    @Override
    public List<StatusMonitoringDto> laboratoryStatusMonitoring() {
        List<StatusMonitoringDto> list = new ArrayList<>();
        List<Laboratory> all = laboratoryMapper.findAll();
        int i = 0;
        for (Laboratory laboratory : all) {
            StatusMonitoringDto statusMonitoringDto = new StatusMonitoringDto();
            statusMonitoringDto = laboratoryMapper.laboratoryStatusMonitoring(laboratory.getId());
            if(i<6&&statusMonitoringDto!=null){
                list.add(statusMonitoringDto);
                i++;
            }else if(i>=6){
                break;
            }
        }
        return list;
    }

    @Override
    public List<StatusMonitoringDto> laboratoryStatusMonitoringAll() {
        List<StatusMonitoringDto> list = new ArrayList<>();
        List<Laboratory> all = laboratoryMapper.findAll();
        for (Laboratory laboratory : all) {
            StatusMonitoringDto statusMonitoringDto = new StatusMonitoringDto();
            statusMonitoringDto = laboratoryMapper.laboratoryStatusMonitoring(laboratory.getId());
            if(statusMonitoringDto!=null) {
                list.add(statusMonitoringDto);
            }
        }
        return list;
    }

    @Override
    public Map<Object, Object> pageLaboratoryStatusMonitoringAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StatusMonitoringDto> list = new ArrayList<>();
        List<Laboratory> all = laboratoryMapper.findAll();
        for (Laboratory laboratory : all) {
            StatusMonitoringDto statusMonitoringDto = new StatusMonitoringDto();
            statusMonitoringDto = laboratoryMapper.laboratoryStatusMonitoring(laboratory.getId());
            if(statusMonitoringDto!=null){
                list.add(statusMonitoringDto);
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap =  new HashMap<>();
        allMap.put("data",list);
        allMap.put("total",total);

        return allMap;
    }

    @Override
    public Map<Object, Object> likeFindAll(int pageNum, int pageSize, String like) {
        PageHelper.startPage(pageNum, pageSize);
        List<Laboratory> all = laboratoryMapper.likeFindAll(like);
        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap =  new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;
    }

    @Override
    public Map<Object, Object> likeFindAll(String like) {
        List<Laboratory> all = laboratoryMapper.likeFindAll(like);

        Map<Object,Object> allMap =  new HashMap<>();
        allMap.put("data",all);
        allMap.put("tatol",all.size());

        return allMap;
    }

    @Override
    public Map<Object, Object> findAllLaboratory(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Laboratory> all = laboratoryMapper.findAll();
        PageInfo pageInfo = new PageInfo(all);
        long total = pageInfo.getTotal();

        Map<Object,Object> allMap =  new HashMap<>();
        allMap.put("data",all);
        allMap.put("total",total);

        return allMap;

    }
}
