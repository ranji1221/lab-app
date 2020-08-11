package com.ranji.lab.controller;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * 部署流程定义
 */
@Controller
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    RepositoryService repositoryService;

    @RequestMapping("/deploy")
    @ResponseBody
    public String deployFlow(String fileName){
        DeploymentBuilder builder = repositoryService.createDeployment()
                .addClasspathResource("process"+File.separator+fileName);
        Deployment deployment = builder.deploy();
        return "deploy success.";
    }

    @RequestMapping("/list")
    public String listFlow(){
        return "process/list";
    }

}
