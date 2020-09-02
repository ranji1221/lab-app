package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ArrangeDto;
import com.ranji.lab.dto.ProjectConsumeDto;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.LaboratoryDevice;
import com.ranji.lab.entity.ProjectDevice;
import com.ranji.lab.mapper.ArrangeMapper;
import com.ranji.lab.mapper.LaboratoryDeviceMapper;
import com.ranji.lab.mapper.ProjectConsumeMapper;
import com.ranji.lab.mapper.ProjectDeviceMapper;
import com.ranji.lab.service.prototype.IArrangeService;
import com.ranji.lab.service.prototype.IDeviceService;
import com.ranji.lab.service.prototype.ILaboratoryDeviceService;
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
    @Resource
    LaboratoryDeviceMapper laboratoryDeviceMapper;

    //插入预约信息
    @Override
    @Transactional
    public int insertArrange(Arrange arrange,String devices,String consumes) {
        List<ProjectDeviceDto> projectDeviceDtos = JSON.parseObject(devices, new TypeReference<ArrayList<ProjectDeviceDto>>() {});
        List<ProjectConsumeDto> projectConsumeDtos = JSON.parseObject(consumes, new TypeReference<ArrayList<ProjectConsumeDto>>() {});
        int i = arrangeMapper.insertArrange(arrange);
        for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                projectDeviceDto.setArrangeProjectId(arrange.getId());
                projectDeviceMapper.insertProjectDevice(projectDeviceDto);
        }
        for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
            projectConsumeDto.setArrangeProjectId(arrange.getId());
            projectConsumeMapper.insertProjectConsume(projectConsumeDto);
        }
        return i;
    }

    //查询全部预约实验项目运
    @Override
    public List<ArrangeDto> findAllArrange(Integer status) {
        List<ArrangeDto> allArrange = arrangeMapper.findAllArrange(status);
        for (ArrangeDto arrangeDto : allArrange) {
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(arrangeDto.getProjectId());
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(arrangeDto.getProjectId());
            StringBuffer devices = new StringBuffer();
            StringBuffer consumes = new StringBuffer();
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                consumes.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+projectConsumeDto.getUnitName()+"、");
            }
            arrangeDto.setConsumes(consumes.toString().substring(0,consumes.toString().length()-1));
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                devices.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+projectDeviceDto.getUnitName()+"、");
            }
            arrangeDto.setDevices(devices.toString().substring(0,devices.toString().length()-1));
        }
        return allArrange;
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
                consumes.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+projectConsumeDto.getUnitName()+"、");
            }
            arrangeDto.setConsumes(consumes.toString().substring(0,consumes.toString().length()-1));
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(arrangeDto.getProjectId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                devices.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+projectDeviceDto.getUnitName()+"、");
            }
            arrangeDto.setDevices(devices.toString().substring(0,devices.toString().length()-1));
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

    //修改
    @Override
    @Transactional
    public int updArrange(Arrange arrange,String devices,String consumes) {
        List<ProjectDeviceDto> projectDeviceDtos = JSON.parseObject(devices, new TypeReference<ArrayList<ProjectDeviceDto>>() {});
        List<ProjectConsumeDto> projectConsumeDtos = JSON.parseObject(consumes, new TypeReference<ArrayList<ProjectConsumeDto>>() {});
        int i = arrangeMapper.updArrange(arrange);
        for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
            projectDeviceDto.setArrangeProjectId(arrange.getId());
            projectDeviceMapper.updProjectDevice(projectDeviceDto);
        }
        for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
            projectConsumeDto.setArrangeProjectId(arrange.getId());
            projectConsumeMapper.updProjectConsume(projectConsumeDto);
        }
        return i;
    }

    @Override
    public List<ArrangeDto> yesOrNoArrange(Arrange arrange) {
        List<ArrangeDto> arrangeDtos = arrangeMapper.yesOrNoArrange(arrange);
        return arrangeDtos;
    }

    @Override
    public Map<Object, Object> pageFindlikeFindArrange(String like) {
        List<ArrangeDto> allArrange = arrangeMapper.likeFindArrange(like);
        for (ArrangeDto arrangeDto : allArrange) {
            StringBuffer consumes = new StringBuffer();
            StringBuffer devices = new StringBuffer();
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(arrangeDto.getProjectId());
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                consumes.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+projectConsumeDto.getUnitName()+"、");
            }
            arrangeDto.setConsumes(consumes.toString().substring(0,consumes.toString().length()-1));
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(arrangeDto.getProjectId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                devices.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+projectDeviceDto.getUnitName()+"、");
            }
            arrangeDto.setDevices(devices.toString().substring(0,devices.toString().length()-1));
        }
        Map<Object,Object> map = new HashMap<>();
        map.put("data",allArrange);
        return map;
    }

    @Override
    public List<ArrangeDto> statusAndNum() {
        return arrangeMapper.statusAndNum();
    }
}
