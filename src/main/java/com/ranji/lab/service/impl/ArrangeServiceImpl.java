package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.Arrange;
import com.ranji.lab.entity.Device;
import com.ranji.lab.entity.LaboratoryDevice;
import com.ranji.lab.entity.ProjectDevice;
import com.ranji.lab.mapper.*;
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
    private ArrangeMapper arrangeMapper;
    @Resource
    private BackStageDtoMapper backStageDtoMapper;
    @Resource
    private ProjectConsumeMapper projectConsumeMapper;
    @Resource
    private ProjectDeviceMapper projectDeviceMapper;
    @Resource
    private LaboratoryDeviceMapper laboratoryDeviceMapper;
    @Resource
    private DeviceMapper deviceMapper;

    //插入预约信息
    @Override
    @Transactional
    public int insertArrange(Arrange arrange, String devices, String consumes) {
        List<ProjectDeviceDto> projectDeviceDtos = JSON.parseObject(devices, new TypeReference<ArrayList<ProjectDeviceDto>>() {
        });
        List<ProjectConsumeDto> projectConsumeDtos = JSON.parseObject(consumes, new TypeReference<ArrayList<ProjectConsumeDto>>() {
        });
        int i = arrangeMapper.insertArrange(arrange);
        for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
            List<DeviceAndDeviceTypeNameDto> noAllocationDeviceByLaboratoryId = deviceMapper.findNoAllocationDeviceByLaboratoryId(projectDeviceDto.getExperimentDeviceId(), arrange.getLaboratoryId(), projectDeviceDto.getDeviceNum());
            for (DeviceAndDeviceTypeNameDto device : noAllocationDeviceByLaboratoryId) {
                projectDeviceDto.setArrangeProjectId(arrange.getId());
                projectDeviceDto.setExperimentDeviceId(device.getDeviceId());
                projectDeviceMapper.insertProjectDevice(projectDeviceDto);
            }
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
            if (projectConsumeDtos.size() > 0) {
                for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                    if (projectConsumeDto != null) {
                        consumes.append(projectConsumeDto.getConsumeName() + ":" + projectConsumeDto.getConsumeNum() + projectConsumeDto.getUnitName() + "、");
                    }
                }
                arrangeDto.setConsumes(consumes.toString().substring(0, consumes.toString().length() - 1));
            }
            if (projectDeviceDtos.size() > 0) {
                for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                    if (projectDeviceDto != null) {
                        devices.append(projectDeviceDto.getDeviceName() + ":" + projectDeviceDto.getDeviceNum() + projectDeviceDto.getUnitName() + "、");
                    }
                }
                arrangeDto.setDevices(devices.toString().substring(0, devices.toString().length() - 1));
            }
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
            if (projectConsumeDtos.size() > 0) {
                for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                    consumes.append(projectConsumeDto.getConsumeName() + ":" + projectConsumeDto.getConsumeNum() + projectConsumeDto.getUnitName() + "、");
                }
                arrangeDto.setConsumes(consumes.toString().substring(0, consumes.toString().length() - 1));
            }
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(arrangeDto.getProjectId());
            if (projectDeviceDtos.size() > 0) {
                for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                    devices.append(projectDeviceDto.getDeviceName() + ":" + projectDeviceDto.getDeviceNum() + projectDeviceDto.getUnitName() + "、");
                }
                arrangeDto.setDevices(devices.toString().substring(0, devices.toString().length() - 1));
            }
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
    public Map<Object, Object> pageFindlikeFindArrange(int pageNum, int pageSize, String like) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArrangeDto> allArrange = arrangeMapper.likeFindArrange(like);
        for (ArrangeDto arrangeDto : allArrange) {
            StringBuffer consumes = new StringBuffer();
            StringBuffer devices = new StringBuffer();
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(arrangeDto.getProjectId());
            if (projectConsumeDtos.size() > 0) {
                for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                    consumes.append(projectConsumeDto.getConsumeName() + ":" + projectConsumeDto.getConsumeNum() + projectConsumeDto.getUnitName() + "、");
                }
                arrangeDto.setConsumes(consumes.toString().substring(0, consumes.toString().length() - 1));
            }
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(arrangeDto.getProjectId());
            if (projectDeviceDtos.size() > 0) {
                for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                    devices.append(projectDeviceDto.getDeviceName() + ":" + projectDeviceDto.getDeviceNum() + projectDeviceDto.getUnitName() + "、");
                }
                arrangeDto.setDevices(devices.toString().substring(0, devices.toString().length() - 1));

            }
        }
        PageInfo pageInfo = new PageInfo(allArrange);
        long total = pageInfo.getTotal();
        Map<Object, Object> map = new HashMap<>();
        map.put("data", allArrange);
        map.put("total", total);
        return map;
    }

    @Override
    public List<ArrangeDto> statusAndNum() {
        return arrangeMapper.statusAndNum();
    }

    @Override
    @Transactional
    public void changeArrangeStatus() {
        String date = backStageDtoMapper.findNowDays(0);
        String time = backStageDtoMapper.findNowTime();
        arrangeMapper.changeArrangeProjectToFinished(date, time);
        arrangeMapper.changeArrangeProjectToOngoing(date, time);
        //查询需要修改为正在进行项目的预约id
        List<Integer> projectIdByChangeArrangeProjectToOngoing = arrangeMapper.findProjectIdByChangeArrangeProjectToOngoing(date, time);
        //查询需要修改为已完成项目的预约id
        List<Integer> projectIdByChangeArrangeProjectToFinished = arrangeMapper.findProjectIdByChangeArrangeProjectToFinished(date, time);
        if (projectIdByChangeArrangeProjectToOngoing.size() > 0) {
            for (Integer integer : projectIdByChangeArrangeProjectToOngoing) {
                List<LaboratoryDeviceNumDto> laboratoryDeviceNumDtos = deviceMapper.laboratoryIdFindDevice(integer, 0);
                if (laboratoryDeviceNumDtos.size() > 0) {
                    for (LaboratoryDeviceNumDto laboratoryDeviceNumDto : laboratoryDeviceNumDtos) {
                        Device device = new Device();
                        device.setId(laboratoryDeviceNumDto.getId());
                        device.setStatus(1);
                        deviceMapper.updateDevice(device);
                    }
                }
            }
        }
        if (projectIdByChangeArrangeProjectToFinished.size() > 0) {
            for (Integer integer : projectIdByChangeArrangeProjectToFinished) {
                List<LaboratoryDeviceNumDto> laboratoryDeviceNumDtos = deviceMapper.laboratoryIdFindDevice(integer, 1);
                if (laboratoryDeviceNumDtos.size() > 0) {
                    for (LaboratoryDeviceNumDto laboratoryDeviceNumDto : laboratoryDeviceNumDtos) {
                        Device device = new Device();
                        device.setId(laboratoryDeviceNumDto.getId());
                        device.setStatus(0);
                        deviceMapper.updateDevice(device);
                    }
                }
            }
        }
    }
}
