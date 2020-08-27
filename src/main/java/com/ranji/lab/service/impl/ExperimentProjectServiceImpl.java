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
public class ExperimentProjectServiceImpl implements IExperimentProjectService {

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
     * @return
     */
    @Override
    @Transactional
    public int insertExperimentProject(ExperimentProject experimentProject) {
        int i = experimentProjectMapper.insertExperimentProject(experimentProject);
        return i;
    }
    //查询全部
    @Override
    public List<ExperimentProjectDto> findAllExperimentProject(Integer status) {
        List<ExperimentProjectDto> allExperimentProject = experimentProjectMapper.findAllExperimentProject(status);
        for (ExperimentProjectDto experimentProjectDto : allExperimentProject) {
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(experimentProjectDto.getId());
            StringBuffer projectConsumeLists = new StringBuffer();
            StringBuffer projectDeviceLists = new StringBuffer();
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                projectConsumeLists.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+projectConsumeDto.getUnitName()+"、");
            }
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(experimentProjectDto.getId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                projectDeviceLists.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+projectDeviceDto.getUnitName()+"、");
            }
            if(projectConsumeLists.toString().length()>0){
                experimentProjectDto.setProjectConsumeLists(projectConsumeLists.toString().substring(0,projectConsumeLists.toString().length()-1));
            }
            if(projectDeviceLists.toString().length()>0){
                experimentProjectDto.setProjectDeviceLists(projectDeviceLists.toString().substring(0,projectDeviceLists.toString().length()-1));
            }
        }
        return allExperimentProject;
    }
    //分页查询
    @Override
    public Map<Object,Object> pageExperimentProject(int pageNum, int pageSize,Integer status) {
        PageHelper.startPage(pageNum,pageSize);
        List<ExperimentProjectDto> allExperimentProject = experimentProjectMapper.findAllExperimentProject(status);
        for (ExperimentProjectDto experimentProjectDto : allExperimentProject) {
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(experimentProjectDto.getId());
            StringBuffer projectConsumeLists = new StringBuffer();
            StringBuffer projectDeviceLists = new StringBuffer();
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                projectConsumeLists.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+projectConsumeDto.getUnitName()+"、");
            }
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(experimentProjectDto.getId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                projectDeviceLists.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+projectDeviceDto.getUnitName()+"、");
            }
            if(projectConsumeLists.toString().length()>0){
                experimentProjectDto.setProjectConsumeLists(projectConsumeLists.toString().substring(0,projectConsumeLists.toString().length()-1));
            }
            if(projectDeviceLists.toString().length()>0){
                experimentProjectDto.setProjectDeviceLists(projectDeviceLists.toString().substring(0,projectDeviceLists.toString().length()-1));
            }
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
        experimentProjectDto.setProjectDeviceList(projectDeviceMapper.projectIdFindProjectDeviceNum(id));
        experimentProjectDto.setProjectConsumeList(projectConsumeMapper.projectIdFindAllProjectConsume(id));
        return experimentProjectDto;
    }

    /**
     * 修改
     */
    @Override
    @Transactional
    public int updExperimentProject(ExperimentProject experimentProject, String p1) {
            List<ProjectConsumeDto> projectConsumeDtos = JSON.parseObject(p1, new TypeReference<ArrayList<ProjectConsumeDto>>() {});

            /*for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                if(projectConsumeDto.getStatus()==0){
                    projectConsumeMapper.insertProjectConsume(projectConsumeDto);
                }else if(projectConsumeDto.getStatus()==1){
                    projectConsumeMapper.updProjectConsume(projectConsumeDto);
                }
            }*/
        return experimentProjectMapper.updExperimentProject(experimentProject);
    }

    @Override
    public Map<Object, Object> findLikeExperimentProject(String like, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<ExperimentProjectDto> allExperimentProject = experimentProjectMapper.findLikeExperimentProject(like);
        for (ExperimentProjectDto experimentProjectDto : allExperimentProject) {
            List<ProjectConsumeDto> projectConsumeDtos = projectConsumeMapper.projectIdFindAllProjectConsume(experimentProjectDto.getId());
            StringBuffer projectConsumeLists = new StringBuffer();
            StringBuffer projectDeviceLists = new StringBuffer();
            for (ProjectConsumeDto projectConsumeDto : projectConsumeDtos) {
                projectConsumeLists.append(projectConsumeDto.getConsumeName()+":"+projectConsumeDto.getConsumeNum()+projectConsumeDto.getUnitName()+"、");
            }
            List<ProjectDeviceDto> projectDeviceDtos = projectDeviceMapper.projectIdFindProjectDeviceNum(experimentProjectDto.getId());
            for (ProjectDeviceDto projectDeviceDto : projectDeviceDtos) {
                projectDeviceLists.append(projectDeviceDto.getDeviceName()+":"+projectDeviceDto.getDeviceNum()+projectDeviceDto.getUnitName()+"、");
            }
            if(projectConsumeLists.toString().length()>0){
                experimentProjectDto.setProjectConsumeLists(projectConsumeLists.toString().substring(0,projectConsumeLists.toString().length()-1));
            }
            if(projectDeviceLists.toString().length()>0){
                experimentProjectDto.setProjectDeviceLists(projectDeviceLists.toString().substring(0,projectDeviceLists.toString().length()-1));
            }
        }
        PageInfo<ExperimentProjectDto> objectPageInfo = new PageInfo<>(allExperimentProject);
        long total = objectPageInfo.getTotal();
        Map<Object,Object> map = new HashMap<>();
        map.put("total",total);
        map.put("data",allExperimentProject);
        return map;
    }
}
