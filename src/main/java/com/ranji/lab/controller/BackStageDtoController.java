package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.BackStage3Dto;
import com.ranji.lab.service.prototype.IBackStageDtoService;
import com.ranji.lab.service.prototype.IDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "后台主页展示数据接口")
@RestController
public class BackStageDtoController {

    @Resource
    private IBackStageDtoService iBackStageDtoService;
    @Resource
    private IDeviceService iDeviceService;

    @ApiOperation(value = "查询折线图", notes = "查询折线图")
    @GetMapping(value = "/findlinechart", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"laboratoryMgr", "admin", "teacher", "majorHead", "manager"}, logical = Logical.OR)
    public String findLineChart() {
        /**
         * 为了演示需求，临时做了个判断，可以删除
         */
        boolean flag = false;

        String rootDirectory = System.getProperty("user.dir");
        File file = new File(rootDirectory + File.separator + "exhibition.ini");

        if (file.exists())
            flag = true;
        Map<Object, Object> allMap = new HashMap<>();
        if (flag)
            //演示的方法，可以删除
            allMap = iBackStageDtoService.findSevenDaysAgoDataDemo();
        else
            allMap = iBackStageDtoService.findSevenDaysAgoData();

        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "查询四格图", notes = "查询四格图")
    @GetMapping(value = "/findfourchart", produces = "text/plain;charset=utf-8")
    //@RequiresRoles(value = {"laboratoryMgr", "admin", "teacher", "majorHead", "manager"}, logical = Logical.OR)
    public String findFourChart() {
        /**
         * 为了演示需求，临时做了个判断，可以删除
         */
        boolean flag = false;
        String rootDirectory = System.getProperty("user.dir");
        File file = new File(rootDirectory + File.separator + "exhibition.ini");

        if (file.exists())
            flag = true;
        Map<Object, Object> allMap = new HashMap<>();
        if (flag)
            //演示的方法，可以删除
            allMap = iBackStageDtoService.findAllAndFinishedAndUnfinishedAndNoCountDataDemo();
        else
            allMap = iBackStageDtoService.findAllAndFinishedAndUnfinishedAndNoCountData();

        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "查询扇形图", notes = "查询扇形图")
    @RequiresRoles(value = {"laboratoryMgr", "admin", "teacher", "majorHead", "manager"}, logical = Logical.OR)
    @GetMapping(value = "/findsectorchart", produces = "text/plain;charset=utf-8")
    public String findSectorChart() {
        Map<Object, Object> statusAndSum = iDeviceService.findStatusAndSum();
        return JSON.toJSONString(statusAndSum);
    }
}
