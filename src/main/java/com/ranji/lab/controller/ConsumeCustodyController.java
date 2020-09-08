package com.ranji.lab.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.dto.ConsumeCustodyInsertDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.entity.ConsumePurchase;
import com.ranji.lab.service.prototype.IConsumeCustodyService;
import com.ranji.lab.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "耗材保管领用接口")
@RestController
public class ConsumeCustodyController {


    @Resource
    private IConsumeCustodyService iConsumeCustodyService;

    @ApiOperation(value="插入保管领用", notes="根据传过来的设备信息来插入保管领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "耗材名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "recipient", value = "领用人", required = true, dataType = "String"),
            @ApiImplicitParam(name = "date", value = "领用日期", required = true, dataType = "String")
    })
    @PostMapping(value = "insertConsumeCustody",produces = "text/plain;charset=utf-8")
    public String insertConsumeCustody(ConsumeCustodyInsertDto consumeCustodyInsertDto){
        Map<Object,Object> insertConsumeCustodyMap = new HashMap<>();
        int i = iConsumeCustodyService.insertConsumeCustody(consumeCustodyInsertDto);
        if(i<1){
            insertConsumeCustodyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(insertConsumeCustodyMap);
        }else{
            insertConsumeCustodyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(insertConsumeCustodyMap);
        }

    }
    @ApiOperation(value="更新保管领用", notes="根据传过来的设备信息来更新保管领用")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "updateConsumeCustody",produces = "text/plain;charset=utf-8")
    public String updateConsumeCustody(ConsumeCustody consumeCustody){
        Map<Object, Object> updateConsumeCustodyMap = new HashMap<>();
        int i = iConsumeCustodyService.updateConsumeCustody(consumeCustody);
        if (i < 1) {
            updateConsumeCustodyMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(updateConsumeCustodyMap);
        } else {
            updateConsumeCustodyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(updateConsumeCustodyMap);
        }

    }

    //修改保管领用状态
    @ApiOperation(value = "更新保管领用状态", notes = "更新保管领用状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "保管领用id", required = true, dataType = "String")
    })
    @PostMapping(value = "updateConsumeCustodyStatus", produces = "text/plain;charset=utf-8")
    public String updateConsumeCustodyStatus(ConsumeCustodyDto consumeCustody, String consumeCustodyss) {
        Map<Object, Object> updateConsumeCustodyMap = new HashMap<>();
        int i = 0;
        if (consumeCustody != null) {
            i = iConsumeCustodyService.updateConsumeCustodyStatus(consumeCustody);
        }
        if (consumeCustodyss != null) {
            ConsumeCustodyDto[] consumeCustodys = JSON.parseObject(consumeCustodyss, new TypeReference<ConsumeCustodyDto[]>() {
            });
            for (ConsumeCustodyDto consumeCustody1 : consumeCustodys) {
                i = iConsumeCustodyService.updateConsumeCustodyStatus(consumeCustody1);
            }
        }
        if (i < 1) {
            updateConsumeCustodyMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(updateConsumeCustodyMap);
        } else {
            updateConsumeCustodyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(updateConsumeCustodyMap);
        }

    }

    @ApiOperation(value = "查找所有的保管领用", notes = "根据传过来的设备信息来更新保管领用")
    @GetMapping(value = "/allconsumecustody", produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustody() {
        HashMap<Object, Object> map = iConsumeCustodyService.findAllConsumeCustodys(0, Integer.MAX_VALUE);
        if (map != null) {
            map.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(map);
        } else {
            map.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(map);
        }
    }
    @ApiOperation(value="获得保管领用包括name", notes="根据传过来的设备信息来获得")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumecustodys",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustodys(int page,int limit){
        HashMap<Object, Object> map = iConsumeCustodyService.findAllConsumeCustodys(page, limit);
        /**
         * 通过id查询名字，并将名字传递至前台
         */
        if (map != null) {
            map.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(map);
        } else {
            map.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(map);
        }
    }

    @ApiOperation(value="分页查询保管领用", notes="根据传过来的设备信息来获得保管领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumecustodypaging", produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustodyPaging(int page, int limit) {
        Map<Object, Object> allConsumeCustodyMap = iConsumeCustodyService.findAll(page, limit);
        if (!allConsumeCustodyMap.isEmpty()) {
            allConsumeCustodyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        } else {
            allConsumeCustodyMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }
    }

    @ApiOperation(value = "按照状态查询保管领用", notes = "根据传过来的信息来获得保管领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", required = true, dataType = "String")
    })
    @GetMapping(value = "/statusFindAllConsumeCustody", produces = "text/plain;charset=utf-8")
    public String statusFindAll(Integer status, int page, int limit) {
        Map<Object, Object> allConsumeCustodyMap = iConsumeCustodyService.statusFindAll(status, page, limit);
        if (!allConsumeCustodyMap.isEmpty()) {
            allConsumeCustodyMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        } else {
            allConsumeCustodyMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }
    }

    /*//分页模糊查询
    @ApiOperation(value="分页模糊查询保管领用", notes="根据传过来的设备信息来获得保管领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    })
    @GetMapping(value = "/likefindAll",produces = "text/plain;charset=utf-8")
    public String likefindAll(int page,int limit,String like){
        Map<Object,Object> allConsumeCustodyMap = iConsumeCustodyService.likefindAll(page,limit,like);
        if(!allConsumeCustodyMap.isEmpty()){
            allConsumeCustodyMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }else{
            allConsumeCustodyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }
    }*/

    //模糊查询
    @ApiOperation(value="模糊查询保管领用", notes="根据传过来的设备信息来获得保管领用")
    @ApiImplicitParam(name = "like", value = "关键字", required = true, dataType = "String")
    @GetMapping(value = "/likefindAll",produces = "text/plain;charset=utf-8")
    public String likefindAll(String like){
        Map<Object,Object> allConsumeCustodyMap = iConsumeCustodyService.likefindAll(like);
        if(!allConsumeCustodyMap.isEmpty()){
            allConsumeCustodyMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }else{
            allConsumeCustodyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }
    }

    @ApiOperation(value="通过实验id查询所有耗材和耗材数量", notes="通过实验室id查询所有耗材和耗材数量")
    @ApiImplicitParam(name = "id", value = "实验id", required = true, dataType = "String")
    @GetMapping(value = "/allconsumeandconsumenum",produces = "text/plain;charset=utf-8")
    public String allConsumeAndConsumenum(int id){
        Map<Object, Object> allConsumeAndConsumeNum = iConsumeCustodyService.findAllConsumeAndConsumeNum(id);
        return JSON.toJSONString(allConsumeAndConsumeNum);
    }
}
