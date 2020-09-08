package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.service.prototype.IBackStageDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@Api(tags = "后台主页展示数据接口")
@RestController
public class BackStageDtoController {

    @Resource
    private IBackStageDtoService iBackStageDtoService;

    @ApiOperation(value="查询折线图", notes="查询折线图")
    @GetMapping(value = "/findlinechart",produces = "text/plain;charset=utf-8")
    public String findLineChart(){
        Map<Object, Object> allMap = iBackStageDtoService.findNowAndLatestSevenDaysData();
        return JSON.toJSONString(allMap);
    }
    @ApiOperation(value="查询四格图", notes="查询四格图")
    @GetMapping(value = "/findfourchart",produces = "text/plain;charset=utf-8")
    public String findFourChart(){
        Map<Object, Object> allMap = iBackStageDtoService.findAllAndFinishedAndUnfinishedAndNoCountData();
        return JSON.toJSONString(allMap);
    }

}
