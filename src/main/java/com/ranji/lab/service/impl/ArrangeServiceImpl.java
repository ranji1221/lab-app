package com.ranji.lab.service.impl;

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

import javax.annotation.Resource;
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
    public int insertArrange(Arrange arrange) {
        return arrangeMapper.insertArrange(arrange);
    }

    @Override
    public List<ArrangeDto> findAllArrange() {
        return arrangeMapper.findAllArrange();
    }

    //分页查询所有的预约实验项目
    @Override
    public Map<Object, Object> pageFindAllArrange(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ArrangeDto> allArrange = arrangeMapper.findAllArrange();
        for (ArrangeDto arrangeDto : allArrange) {
            StringBuffer consumes = new StringBuffer();
            StringBuffer devices = new StringBuffer();
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(arrangeDto.getProjectId());
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                consumes.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum());
            }
            arrangeDto.setConsumes(consumes.toString());
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindAllProjectDevice(arrangeDto.getProjectId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                devices.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum());
            }
            arrangeDto.setDevices(devices.toString());
        }
        PageInfo<ArrangeDto> arrangeDtoPageInfo = new PageInfo<>(allArrange);
        long total = arrangeDtoPageInfo.getTotal();
        Map<Object,Object> map = new HashMap<>();
        map.put("data",allArrange);
        map.put("total",total);
        return map;
    }

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
        return null;
    }
}
