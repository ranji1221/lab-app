package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.ReportRepairDto;
import com.ranji.lab.dto.ReportRepairInsertDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ReportRepair;
import com.ranji.lab.service.prototype.IReportRepairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "设备报修接口")
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
    @PostMapping(value = "/insertreportrepair", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "teacher", "laboratoryMgr", "manager"}, logical = Logical.OR)
    public String insertReportRepair(ReportRepairInsertDto reportRepairInsertDto){
        HashMap<Object, Object> insertReportRepairMap = new HashMap<>();
        int i = reportRepairService.insertReportRepair(reportRepairInsertDto);
        if(i<1){
            insertReportRepairMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(insertReportRepairMap);
        }else{
            insertReportRepairMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
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
    @PostMapping(value = "/updatereportrepair", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin", "majorHead", "laboratoryMgr", "manager"}, logical = Logical.OR)
    public String updateReportRepair(ReportRepair reportRepair){
        HashMap<Object, Object> updateReportRepairMap = new HashMap<>();
        int i = reportRepairService.updateReportRepair(reportRepair);
        if(i<1){
            updateReportRepairMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(updateReportRepairMap);
        }else{
            updateReportRepairMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(updateReportRepairMap);
        }
    }

    @ApiOperation(value="查询所有设备维修", notes="查询所有设备维修")
    @GetMapping(value = "allreportrepair",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllReportRepair(){
        Map<Object, Object> reportRepairMap = reportRepairService.AllReportRepair();
        if(!reportRepairMap.isEmpty()){
            reportRepairMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(reportRepairMap);
        }else{
            reportRepairMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(reportRepairMap);
        }
    }

    @ApiOperation(value="分页查询所有设备维修", notes="分页查询所有设备维修")
    @GetMapping(value = "allreportrepairpaging",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllReportRepairPaging(int page,int limit){
        Map<Object, Object> reportRepairMap = reportRepairService.AllReportRepair(page,limit);
        if(!reportRepairMap.isEmpty()){
            reportRepairMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(reportRepairMap);
        }else{
            reportRepairMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(reportRepairMap);
        }
    }

    @ApiOperation(value="模糊查询所有设备维修", notes="模糊分页查询所有设备维修")
    @GetMapping(value = "likeFinAllReportRepair",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String likeFinAllReportRepair(String like){
        Map<Object, Object> reportRepairMap = reportRepairService.likeFinAllReportRepair(like);
        if(!reportRepairMap.isEmpty()){
            reportRepairMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(reportRepairMap);
        }else{
            reportRepairMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(reportRepairMap);
        }
    }

}
