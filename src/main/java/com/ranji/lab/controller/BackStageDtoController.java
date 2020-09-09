package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.BackStage3Dto;
import com.ranji.lab.service.prototype.IBackStageDtoService;
import com.ranji.lab.service.prototype.IDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public String findLineChart() {
        Map<Object, Object> allMap = iBackStageDtoService.findSevenDaysAgoData();
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "查询四格图", notes = "查询四格图")
    @GetMapping(value = "/findfourchart", produces = "text/plain;charset=utf-8")
    public String findFourChart() {
        Map<Object, Object> allMap = iBackStageDtoService.findAllAndFinishedAndUnfinishedAndNoCountData();
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "查询扇形图", notes = "查询扇形图")
    @GetMapping(value = "/findsectorchart", produces = "text/plain;charset=utf-8")
    public String findSectorChart() {
        List<BackStage3Dto> statusAndSum = iDeviceService.findStatusAndSum();
        return JSON.toJSONString(statusAndSum);
    }
}
