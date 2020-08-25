package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.ReportRepair;
import com.ranji.lab.service.prototype.IReportRepairService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.experimental.Accessors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;

@RestController
public class ReportRepairController {
    @Resource
    private IReportRepairService reportRepairService;

    @ApiOperation(value="插入所需维修的设备", notes="插入所需设备的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需维修设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(0报修中，1维修结束)",dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", required = true , dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true , dataType = "String")
    })
    @PostMapping(value = "/insertreportrepair",produces = "text/plain;charset=utf-8")
    public String insertReportRepair(ReportRepair reportRepair){
        HashMap<Object, Object> insertReportRepairMap = new HashMap<>();
        int i = reportRepairService.insertReportRepair(reportRepair);
        if(i<1){
            insertReportRepairMap.put("status","failure");
            return JSON.toJSONString(insertReportRepairMap);
        }else{
            insertReportRepairMap.put("status","success");
            return JSON.toJSONString(insertReportRepairMap);
        }
    }

    @ApiOperation(value="更新所需维修的设备", notes="更新所需维修的设备状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "维修表id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "deviceId", value = "所需维修设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(0报修中，1维修结束)",dataType = "String"),
            @ApiImplicitParam(name = "description", value = "描述", required = true , dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true , dataType = "String")
    })
    @PostMapping(value = "/updatereportrepair",produces = "text/plain;charset=utf-8")
    public String updateReportRepair(ReportRepair reportRepair){
        HashMap<Object, Object> updateReportRepairMap = new HashMap<>();
        int i = reportRepairService.updateReportRepair(reportRepair);
        if(i<1){
            updateReportRepairMap.put("status","failure");
            return JSON.toJSONString(updateReportRepairMap);
        }else{
            updateReportRepairMap.put("status","success");
            return JSON.toJSONString(updateReportRepairMap);
        }
    }

}
