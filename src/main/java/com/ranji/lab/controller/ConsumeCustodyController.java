package com.ranji.lab.controller;


import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.ConsumeCustodyDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ConsumeCustody;
import com.ranji.lab.service.prototype.IConsumeCustodyService;
import com.ranji.lab.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
            @ApiImplicitParam(name = "date", value = "领用日期", required = true, dataType = "String"),
            @ApiImplicitParam(name = "count", value = "领用数量", required = true, dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "String")
    })
    @PostMapping(value = "insertConsumeCustody",produces = "text/plain;chartset=utf-8")
    public String insertConsumeCustody(ConsumeCustody consumeCustody){
        Map<Object,Object> insertConsumeCustodyMap = new HashMap<>();
        int i = iConsumeCustodyService.insertConsumeCustody(consumeCustody);
        if(i<1){
            insertConsumeCustodyMap.put("status","failure");
            return JSON.toJSONString(insertConsumeCustodyMap);
        }else{
            insertConsumeCustodyMap.put("status","success");
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
    @PostMapping(value = "updateConsumeCustody",produces = "text/plain;chartset=utf-8")
    public String updateConsumeCustody(ConsumeCustody consumeCustody){
        Map<Object,Object> updateConsumeCustodyMap = new HashMap<>();
        int i = iConsumeCustodyService.updateConsumeCustody(consumeCustody);
        if(i<1){
            updateConsumeCustodyMap.put("status","failure");
            return JSON.toJSONString(updateConsumeCustodyMap);
        }else{
            updateConsumeCustodyMap.put("status","success");
            return JSON.toJSONString(updateConsumeCustodyMap);
        }

    }
    @ApiOperation(value="查找所有的保管领用", notes="根据传过来的设备信息来更新保管领用")
    @GetMapping(value = "/allconsumecustody",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustody(){
        List<ConsumeCustody> allConsumeCustody = iConsumeCustodyService.findAll();
        Map<Object,Object> allConsumeCustodyPaging = new HashMap<>();
        if(!allConsumeCustody.isEmpty()){
            allConsumeCustodyPaging.put("data",allConsumeCustody);
            allConsumeCustodyPaging.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyPaging);
        }else{
            allConsumeCustodyPaging.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeCustodyPaging);
        }
    }
    @ApiOperation(value="获得保管领用包括name", notes="根据传过来的设备信息来获得")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumecustodys",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustodys(int page,int limit){
        Map<Object,Object> map = new HashMap<>();
        List<ConsumeCustody> allConsumeCustodys = iConsumeCustodyService.findAllConsumeCustodys(page, limit);
        /**
         * 通过id查询名字，并将名字传递至前台
         */
        List<ConsumeCustodyDto> allConsumeCustodyss = new ArrayList<>();
        if(!allConsumeCustodys.isEmpty()){

            for (ConsumeCustody allConsumecustody : allConsumeCustodys) {
                int id = allConsumecustody.getId();
                String name = iConsumeCustodyService.findNameById(id).getConsumeName();

                ConsumeCustodyDto consumeCustodyDto = new ConsumeCustodyDto();

                consumeCustodyDto.setRecipient(allConsumecustody.getRecipient());
                consumeCustodyDto.setConsumeName(name);
                consumeCustodyDto.setConsumeId(allConsumecustody.getConsumeId());
                consumeCustodyDto.setDate(DateUtil.DateToString(allConsumecustody.getDate(),"yyyy-MM-dd"));
                consumeCustodyDto.setStatus(allConsumecustody.getStatus());
                consumeCustodyDto.setId(allConsumecustody.getId());
                consumeCustodyDto.setUnitName(allConsumecustody.getUnitName());
                consumeCustodyDto.setCount(allConsumecustody.getCount());
                allConsumeCustodyss.add(consumeCustodyDto);
            }
            int total = iConsumeCustodyService.getCount();
            map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            map.put("data",allConsumeCustodyss);
            map.put("total",total);
            return JSON.toJSONString(map);
        }else{
            map.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(map);
        }
    }

    @ApiOperation(value="分页查询保管领用", notes="根据传过来的设备信息来获得保管领用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "第几页", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "所需要的条数", required = true, dataType = "String")
    })
    @GetMapping(value = "/allconsumecustodypaging",produces = "text/plain;charset=utf-8")
    public String findAllConsumeCustodyPaging(int page,int limit){
        Map<Object,Object> allConsumeCustodyMap = iConsumeCustodyService.findAll(page,limit);
        if(!allConsumeCustodyMap.isEmpty()){
            allConsumeCustodyMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeCustodyMap);
        }else{
            allConsumeCustodyMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
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
    @ApiOperation(value="分页模糊查询保管领用", notes="根据传过来的设备信息来获得保管领用")
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
}
