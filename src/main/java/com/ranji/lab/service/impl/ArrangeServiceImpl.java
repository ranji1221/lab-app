package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.dto.ProjectConsumeDto;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.mapper.ArrangeMapper;
import com.ranji.lab.mapper.ProjectConsumeMapper;
import com.ranji.lab.mapper.ProjectDeviceMapper;
import com.ranji.lab.service.prototype.IArrangeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArrangeServiceImpl implements IArrangeService {
    @Resource
    ArrangeMapper arrangeMapper;
    @Resource
    ProjectConsumeMapper projectConsumeMapper;
    @Resource
    ProjectDeviceMapper projectDeviceMapper;

    @Override
    @Transactional
    public int insertArrange(Arrange arrange,String devices) {
        List<ProjectDeviceDto> projectDeviceDtos = JSON.parseObject(devices, new TypeReference<ArrayList<ProjectDeviceDto>>() {});
        for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {

        }
        return arrangeMapper.insertArrange(arrange);
    }

    @Override
    public List<ArrangeDto> findAllArrange(Integer status) {
        return arrangeMapper.findAllArrange(status);
    }

    //分页查询所有的预约实验项目
    @Override
    public Map<Object, Object> pageFindAllArrange(int pageNum, int pageSize,Integer status) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArrangeDto> allArrange = arrangeMapper.findAllArrange(status);
        for (ArrangeDto arrangeDto : allArrange) {
            StringBuffer consumes = new StringBuffer();
            StringBuffer devices = new StringBuffer();
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(arrangeDto.getProjectId());
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                consumes.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+"、");
            }
            arrangeDto.setConsumes(consumes.toString().substring(0,consumes.toString().length()-1));
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindAllProjectDevice(arrangeDto.getProjectId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                devices.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+"、");
            }
            arrangeDto.setDevices(devices.toString().substring(devices.toString().length()-1));
        }
        PageInfo<ArrangeDto> arrangeDtoPageInfo = new PageInfo<>(allArrange);
        long total = arrangeDtoPageInfo.getTotal();
        Map<Object,Object> map = new HashMap<>();
        map.put("data",allArrange);
        map.put("total",total);
        return map;
    }

    /**
     * 按照id查询
     * @param id
     * @return
     */
    @Override
    public ArrangeDto idFindArrange(int id) {
        return arrangeMapper.idFindArrange(id);
    }

    @Override
    public int updArrange(Arrange arrange) {
        return arrangeMapper.updArrange(arrange);
    }

    @Override
    public List<ArrangeDto> yesOrNoArrange(Arrange arrange) {
        List<ArrangeDto> arrangeDtos = arrangeMapper.yesOrNoArrange(arrange);
        return arrangeDtos;
    }
}
