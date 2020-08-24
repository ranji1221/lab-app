package com.ranji.lab.service.prototype;

import com.ranji.lab.dto.ExperimentProjectDto;
import com.ranji.lab.entity.ExperimentProject;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface IExperimentProjectService {
    //插入
    int insertExperimentProject(ExperimentProject experimentProject, String projectConsumeLists, String projectDeviceLists);
    //查询全部
    List<ExperimentProjectDto> findAllExperimentProject(Integer status);
    //分页查询
    Map<Object,Object> pageExperimentProject(int pageNum, int pageSize,Integer status);
    //按照id查询
    ExperimentProjectDto idFindExperiment_project(int id);
    //修改该项目信息
    int updExperimentProject(ExperimentProject experimentProject, String p1, String p2);
    //模糊查询
    Map<Object,Object> findLikeExperimentProject(String like,int pageNum, int pageSize);
}
