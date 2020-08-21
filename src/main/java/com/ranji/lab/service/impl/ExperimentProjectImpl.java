package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ranji.lab.dto.ExperimentProjectDto;
import com.ranji.lab.dto.ProjectConsumeDto;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ExperimentProject;
import com.ranji.lab.entity.ProjectDevice;
import com.ranji.lab.mapper.ExperimentProjectMapper;
import com.ranji.lab.mapper.ProjectConsumeMapper;
import com.ranji.lab.mapper.ProjectDeviceMapper;
import com.ranji.lab.service.prototype.IExperimentProjectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExperimentProjectImpl implements IExperimentProjectService {

    @Resource
    ExperimentProjectMapper experimentProjectMapper;
    @Resource
    ProjectConsumeMapper projectConsumeMapper;
    @Resource
    ProjectDeviceMapper projectDeviceMapper;
    //插入
    /**
     *
     * @param experimentProject
     * @param p1 耗材信息
     * @param p2 设备信息
     * @return
     */
    @Override
    @Transactional
    public int insertExperimentProject(ExperimentProject experimentProject, String p1, String p2) {
        experimentProjectMapper.insertExperimentProject(experimentProject);
        int i = experimentProjectMapper.latestExperimentProjectData();
        if(i>0){
            List<ProjectConsumeDto> projectConsumeDtos = JSON.parseObject(p1, new TypeReference<ArrayList<ProjectConsumeDto>>() {});
            List<ProjectDeviceDto> projectDeviceDtos = JSON.parseObject(p2, new TypeReference<ArrayList<ProjectDeviceDto>>() {});
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                projectDeviceDto.setProjectId(i);
                projectDeviceMapper.insertProjectDevice(projectDeviceDto);
            }
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                projectConsumeDto.setProjectId(i);
                projectConsumeMapper.insertProjectConsume(projectConsumeDto);
            }
        }
        return i;
    }
    //查询全部
    @Override
    public List<ExperimentProjectDto> findAllExperimentProject() {
        List<ExperimentProjectDto> allExperimentProject = experimentProjectMapper.findAllExperimentProject();
        for (ExperimentProjectDto experimentProjectDto : allExperimentProject) {
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(experimentProjectDto.getId());
            experimentProjectDto.setProjectConsumeList(projectConsumeDtos);
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindAllProjectDevice(experimentProjectDto.getId());
            experimentProjectDto.setProjectDeviceList(projectDeviceDtos);
        }
        return allExperimentProject;
    }
    //分页查询
    @Override
    public Map<Object,Object> pageExperimentProject(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ExperimentProjectDto> allExperimentProject = experimentProjectMapper.findAllExperimentProject();
        for (ExperimentProjectDto experimentProjectDto : allExperimentProject) {
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(experimentProjectDto.getId());
            StringBuffer projectConsumeLists = new StringBuffer();
            StringBuffer projectDeviceLists = new StringBuffer();
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                projectConsumeLists.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+"、");
            }
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindAllProjectDevice(experimentProjectDto.getId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                projectDeviceLists.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+"、");
            }
            experimentProjectDto.setProjectConsumeLists(projectConsumeLists.toString());
            experimentProjectDto.setProjectDeviceLists(projectDeviceLists.toString());
        }
        PageInfo<ExperimentProjectDto> objectPageInfo = new PageInfo<>(allExperimentProject);
        long total = objectPageInfo.getTotal();
        Map<Object,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",allExperimentProject);
        return map;
    }

    //按照id查询
    @Override
    public ExperimentProjectDto idFindExperiment_project(int id) {
        ExperimentProjectDto experimentProjectDto = experimentProjectMapper.idFindExperimentProject(id);
        experimentProjectDto.setProjectDeviceList(projectDeviceMapper.projectIdFindAllProjectDevice(id));
        experimentProjectDto.setProjectConsumeList(projectConsumeMapper.projectIdFindAllProjectConsume(id));
        return experimentProjectDto;
    }
    //过滤已删除数据
    @Override
    public List<ExperimentProjectDto> noDelExperimentProject() {
        return experimentProjectMapper.noDelExperimentProject();
    }
    //按照分类分页查询
    @Override
    public Map<Object, Object> typeExperimentProject(int pageNum, int pageSize, int status) {
        PageHelper.startPage(pageNum,pageSize);
        List<ExperimentProjectDto> allExperimentProject =  experimentProjectMapper.typeExperimentProject(status);
        PageInfo<ExperimentProjectDto> objectPageInfo = new PageInfo<>(allExperimentProject);
        long total = objectPageInfo.getTotal();
        Map<Object,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",allExperimentProject);
        return map;
    }

    /**
     * 修改
     */
    @Override
    @Transactional
    public int updExperimentProject(ExperimentProject experimentProject, String p1, String p2) {
            List<ProjectConsumeDto> projectConsumeDtos = JSON.parseObject(p1, new TypeReference<ArrayList<ProjectConsumeDto>>() {});
            List<ProjectDeviceDto> projectDeviceDtos = JSON.parseObject(p2, new TypeReference<ArrayList<ProjectDeviceDto>>() {});
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                if(projectDeviceDto.getStatus()==0){
                    projectDeviceMapper.insertProjectDevice(projectDeviceDto);
                }else if(projectDeviceDto.getStatus()==1){
                    projectDeviceMapper.updProjectDevice(projectDeviceDto);
                }
            }
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                if(projectConsumeDto.getStatus()==0){
                    projectConsumeMapper.insertProjectConsume(projectConsumeDto);
                }else if(projectConsumeDto.getStatus()==1){
                    projectConsumeMapper.updProjectConsume(projectConsumeDto);
                }
            }
        return experimentProjectMapper.updExperimentProject(experimentProject);
    }
}
