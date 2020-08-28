package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Scrap;
import com.ranji.lab.service.prototype.IScrapService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "设备报废接口")
@RestController
public class ScrapController {

    @Resource
    private IScrapService iScrapService;

    /**
     * 第一次报修(需要上级审核)
     * @param scrap
     * @return
     */
    @ApiOperation(value="插入所需报废的设备", notes="插入所需报废的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需报废设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(2报废中，4报废)",dataType = "String"),
            @ApiImplicitParam(name = "description", value = "报废描述", required = true , dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true , dataType = "String")
    })
    @PostMapping(value = "/insertscrap",produces = "text/plain;charset=utf-8")
    public String insertScrap(Scrap scrap){
        HashMap<Object, Object> insertScrapMap = new HashMap<>();
        int i = iScrapService.insertScrap(scrap);
        if(i<1){
            insertScrapMap.put("status","failure");
            return JSON.toJSONString(insertScrapMap);
        }else{
            insertScrapMap.put("status","success");
            return JSON.toJSONString(insertScrapMap);
        }
    }

    @ApiOperation(value="取消报废)", notes="取消报废")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需报废设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(2报废中，4报废)",dataType = "String"),
            @ApiImplicitParam(name = "description", value = "报废描述", required = true , dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true , dataType = "String"),
            @ApiImplicitParam(name = "id", value = "报废编号", required = true , dataType = "String")
    })
    @PostMapping(value = "/updatescrapvalue",produces = "text/plain;charset=utf-8")
    public String updateScrap(Scrap scrap){
        HashMap<Object, Object> insertScrapMap = new HashMap<>();
        int i = iScrapService.updateScrapValue(scrap);
        if(i<1){
            insertScrapMap.put("status","failure");
            return JSON.toJSONString(insertScrapMap);
        }else{
            insertScrapMap.put("status","success");
            return JSON.toJSONString(insertScrapMap);
        }
    }

    @ApiOperation(value="更改所需报废的设备状态", notes="更改所需报废的值")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceId", value = "所需报废设备id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态(2报废中，4报废)",dataType = "String"),
            @ApiImplicitParam(name = "description", value = "报废描述", required = true , dataType = "String"),
            @ApiImplicitParam(name = "date", value = "修改日期(xxxx-xx-xx)", required = true , dataType = "String"),
            @ApiImplicitParam(name = "id", value = "报废编号", required = true , dataType = "String")
    })
    @PostMapping(value = "/updatescrapstatus",produces = "text/plain;charset=utf-8")
    public String updateScrapStatus(Scrap scrap){
        HashMap<Object, Object> insertScrapMap = new HashMap<>();
        int i = iScrapService.updateScrapStatus(scrap);
        if(i<1){
            insertScrapMap.put("status","failure");
            return JSON.toJSONString(insertScrapMap);
        }else{
            insertScrapMap.put("status","success");
            return JSON.toJSONString(insertScrapMap);
        }
    }

    @ApiOperation(value="查询所有设备报废", notes="查询所有设备报废")
    @GetMapping(value = "/allscrap",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllScrap(){
        Map<Object, Object> allMap = iScrapService.allScrap();
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value="分页查询所有设备报废", notes="查询所有设备报废")
    @GetMapping(value = "/allscrappaging",produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String findAllScrapPaging(int page,int limit){
        Map<Object, Object> allMap = iScrapService.allScrap(page,limit);
        return JSON.toJSONString(allMap);
    }
}
