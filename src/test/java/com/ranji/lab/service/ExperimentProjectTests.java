package com.ranji.lab.service;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.ExperimentProjectDto;
import com.ranji.lab.dto.ProjectConsumeDto;
import com.ranji.lab.dto.ProjectDeviceDto;
import com.ranji.lab.entity.ExperimentProject;
import com.ranji.lab.service.prototype.IExperimentProjectService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExperimentProjectTests {
    @Resource
    private IExperimentProjectService experimentProjectService;

    @Test
    public void testInsert(){
        ExperimentProject e = new ExperimentProject();
        e.setExperimentTarget("123");
        e.setExperimentProcess("456");
        e.setExperimentName("789");
        e.setExperimentContent("741");
        List<ProjectConsumeDto> l1 = new ArrayList<>();
        List<ProjectDeviceDto> l2 = new ArrayList<>();
        for(int i=0;i<5;i++){
            ProjectConsumeDto p1 = new ProjectConsumeDto();
            ProjectDeviceDto p2 = new ProjectDeviceDto();
            p1.setConsumeNum(i*2);
            p1.setExperimentConsumeId(i);
            p2.setDeviceNum(i*2);
            p2.setExperimentDeviceId(i);
            l1.add(p1);
            l2.add(p2);
        }
        int i = experimentProjectService.insertExperimentProject(e);
        System.out.println(i);

    }
    @Test
    public void testFindAll(){
        List<ExperimentProjectDto> allExperimentProject = experimentProjectService.findAllExperimentProject(1);
        for (ExperimentProjectDto experimentProjectDto : allExperimentProject) {
            System.out.print("id:"+experimentProjectDto.getId()+"\t");
            System.out.print("id:"+experimentProjectDto.getExperimentContent()+"\t");
            System.out.print("id:"+experimentProjectDto.getExperimentName()+"\t");
            System.out.print("id:"+experimentProjectDto.getExperimentProcess()+"\t");
            System.out.print("id:"+experimentProjectDto.getExperimentTarget()+"\t");
            for (ProjectConsumeDto projectConsumeDto : experimentProjectDto.getProjectConsumeList()) {
                System.out.println(projectConsumeDto.getConsumeName()+","+projectConsumeDto.getConsumeNum());
            }
            for (ProjectDeviceDto projectDeviceDto : experimentProjectDto.getProjectDeviceList()) {
                System.out.println(projectDeviceDto.getDeviceName()+","+projectDeviceDto.getDeviceNum());
            }
        }
    }
    @Test
    public void testIdFindAll(){
        ExperimentProjectDto experimentProjectDto = experimentProjectService.idFindExperiment_project(1);
        System.out.print("id:"+experimentProjectDto.getId()+"\t");
        System.out.print("id:"+experimentProjectDto.getExperimentContent()+"\t");
        System.out.print("id:"+experimentProjectDto.getExperimentName()+"\t");
        System.out.print("id:"+experimentProjectDto.getExperimentProcess()+"\t");
        System.out.print("id:"+experimentProjectDto.getExperimentTarget()+"\t");
        for (ProjectConsumeDto projectConsumeDto : experimentProjectDto.getProjectConsumeList()) {
            System.out.println(projectConsumeDto.getConsumeName()+","+projectConsumeDto.getConsumeNum());
        }
        for (ProjectDeviceDto projectDeviceDto : experimentProjectDto.getProjectDeviceList()) {
            System.out.println(projectDeviceDto.getDeviceName()+","+projectDeviceDto.getDeviceNum());
        }
    }
}
