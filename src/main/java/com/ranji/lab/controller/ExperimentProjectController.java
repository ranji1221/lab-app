package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.*;
import com.ranji.lab.entity.*;
import com.ranji.lab.service.prototype.*;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "实验项目接口")
@RestController
public class ExperimentProjectController {

    @Resource
    private IExperimentProjectService iExperimentProjectService;
    /**
     * 添加实验项目信息
     * @return
     */
    @ApiOperation(value="插入实验项目信息", notes="根据传过来的实验项目信息来插入实验项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "experimentName", value = "实验名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentTarget", value = "实验目标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentContent", value = "实验内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentProcess", value = "实验流程", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectConsumeList", value = "需要的耗材信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectDeviceList", value = "需要的设备信息", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "insertExperimentProject")
    @ResponseBody
    public String insertExperimentProject(ExperimentProject experimentProject,String p1,String p2){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iExperimentProjectService.insertExperimentProject(experimentProject,p1,p2);
        if(i<1){
            insertNewsMap.put("status","failure");
            return JSON.toJSONString(insertNewsMap);
        }else{
            insertNewsMap.put("status","success");
            return JSON.toJSONString(insertNewsMap);
        }
    }

    /**
     * 查询所有项目信息
     * @return
     */
    @ApiOperation(value="查询所有实验项目", notes="前端通过访问接口获得所有实验项目")
    @GetMapping(value = "/findAllExperimentProject",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustody(){
        List<ExperimentProjectDto> allExperimentProject = iExperimentProjectService.findAllExperimentProject();
        Map<Object,Object> newsMap = new HashMap<>();
        if(!allExperimentProject.isEmpty()) {
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", allExperimentProject);
            return JSON.toJSONString(newsMap);
        }else{
            newsMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsMap);
        }
    }

    /**
     * 分页查询所有实验项目
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="查询所有实验项目", notes="根据传过来的信息来查询实验项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
    })
    @GetMapping(value = "/pageExperimentProject",produces = "text/plain;charset=utf-8")
    public Object pageExperimentProject(int page,int limit){
        Map<Object, Object> pageExperimentProject = iExperimentProjectService.pageExperimentProject(page, limit);
        if(!pageExperimentProject.isEmpty()) {
            pageExperimentProject.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }else{
            pageExperimentProject.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }
    }
    /**
     * 分类分页查询所有实验项目
     * @param page
     * @param limit
     * @return
     */
    @ApiOperation(value="查询所有实验项目", notes="根据传过来的信息来查询实验项目")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
    })
    @GetMapping(value = "/typePageExperimentProject",produces = "text/plain;charset=utf-8")
    public Object typePageExperimentProject(int page,int limit,int status){
        Map<Object, Object> pageExperimentProject = iExperimentProjectService.typeExperimentProject(page, limit, status);
        if(!pageExperimentProject.isEmpty()) {
            pageExperimentProject.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }else{
            pageExperimentProject.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(pageExperimentProject);
        }
    }
    /**
     * 过滤掉删除的项目
     * @return
     */
    @ApiOperation(value="过滤掉删除的实验项目", notes="前端通过访问接口获得所有实验项目")
    @GetMapping(value = "/noDelExperimentProject",produces = "text/plain;charset=utf-8")
    public String noDelExperimentProject(){
        List<ExperimentProjectDto> allExperimentProject = iExperimentProjectService.noDelExperimentProject();
        Map<Object,Object> newsMap = new HashMap<>();
        if(!allExperimentProject.isEmpty()) {
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", allExperimentProject);
            return JSON.toJSONString(newsMap);
        }else{
            newsMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(newsMap);
        }
    }

    @ApiOperation(value="按照id查询的实验项目", notes="前端通过访问接口获得所有实验项目")
    @GetMapping(value = "/idFindExperiment_project",produces = "text/plain;charset=utf-8")
    public String idFindExperiment_project(int id){
        ExperimentProjectDto experimentProject = iExperimentProjectService.idFindExperiment_project(id);
        Map<Object,Object> newsMap = new HashMap<>();
        if(experimentProject!=null){
            newsMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            newsMap.put("data", experimentProject);
        }
        return JSON.toJSONString(newsMap);
    }

    /**
     * 修改实验项目信息
     * @return
     */
    @ApiOperation(value="修改项目信息", notes="根据传过来的实验项目信息来插入实验项目信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "实验id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentName", value = "实验名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentTarget", value = "实验目标", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentContent", value = "实验内容", required = true, dataType = "String"),
            @ApiImplicitParam(name = "experimentProcess", value = "实验流程", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectConsumeList", value = "需要的耗材信息", required = true, dataType = "String"),
            @ApiImplicitParam(name = "projectDeviceList", value = "需要的设备信息", required = true, dataType = "String")
    })
    @ApiResponses({
            @ApiResponse(code=200,message="成功"),
            @ApiResponse(code=500,message="服务器错误")
    })
    @PostMapping(value = "updExperimentProject")
    @ResponseBody
    public String updExperimentProject(ExperimentProject experimentProject,String p1,String p2){
        Map<Object,Object> insertNewsMap = new HashMap<>();
        int i = iExperimentProjectService.updExperimentProject(experimentProject,p1,p2);
        if(i<1){
            insertNewsMap.put("status","failure");
            return JSON.toJSONString(insertNewsMap);
        }else{
            insertNewsMap.put("status","success");
            return JSON.toJSONString(insertNewsMap);
        }
    }
}
