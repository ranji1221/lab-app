package com.ranji.lab.controller;


import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.ConsumeNormDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.ConsumeNorm;
import com.ranji.lab.service.prototype.IConsumeNormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "耗材管理标准接口")
@RestController
public class ConsumeNormController {

    @Resource
    private IConsumeNormService iConsumeNormService;

    @ApiOperation(value="插入管理标准", notes="根据传过来的设备信息来插入管理标准")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "/insertconsumenorm", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"laboratoryMgr", "admin", "majorHead"}, logical = Logical.OR)
    public String insertConsumeNorm(ConsumeNormDto consumeNormDto){
        Map<Object, Object> insertConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.insertConsumeNorm(consumeNormDto);
        if(i<1){
            insertConsumeNormMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(insertConsumeNormMap);
        }else{
            insertConsumeNormMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(insertConsumeNormMap);
        }
    }

    @ApiOperation(value="更新管理标准", notes="根据传过来的设备信息来更新管理标准")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "题目", required = true, dataType = "String"),
            @ApiImplicitParam(name = "informationSource", value = "信息来源", required = true, dataType = "String"),
            @ApiImplicitParam(name = "author", value = "作者", required = true, dataType = "String"),
            @ApiImplicitParam(name = "time", value = "添加日期", required = true, dataType = "String"),
    })*/
    @PostMapping(value = "/updateconsumenorm", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"laboratoryMgr", "admin", "majorHead"}, logical = Logical.OR)
    public String updateConsumeNorm(ConsumeNorm consumeNorm){
        Map<Object, Object> updateConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.updateConsumeNorm(consumeNorm);
        if(i<1){
            updateConsumeNormMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(updateConsumeNormMap);
        }else{
            updateConsumeNormMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            return JSON.toJSONString(updateConsumeNormMap);
        }
    }

    @ApiOperation(value="分页查找管理标准", notes="根据传过来的设备信息来获取管理标准")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "页数", required = true, dataType = "String"),
            @ApiImplicitParam(name = "limit", value = "每页几条", required = true, dataType = "String")
    })
    @GetMapping(value = "/consumenormpaging",produces = "text/plain;charset=utf-8")
    public String findAllConsumeNormpaging(int page,int limit){
        Map<Object,Object> allConsumeNormMap = iConsumeNormService.findAllConsumeNormPaging(page,limit);
        if(!allConsumeNormMap.isEmpty()){
            allConsumeNormMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
            return JSON.toJSONString(allConsumeNormMap);
        }else{
            allConsumeNormMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
            return JSON.toJSONString(allConsumeNormMap);
        }
    }

    @ApiOperation(value="所有的管理标准及内容", notes="根据传过来的设备信息来获取管理标准及内容")
    @GetMapping(value="/consumenormcontent",produces = "text/plain;charset=utf-8")
    public String findAllConsumeNormContent(){
        ConsumeNorm all = iConsumeNormService.findAll();
        Map<String,Object> map = new HashMap<>();
        map.put("data",all);
        return JSON.toJSONString(map);
    }

















    /*@ApiOperation(value="插入管理标准bug", notes="根据传过来的设备信息来插入管理标准")
    @PostMapping(value = "/insertconsumenormbug",produces = "text/plain;charset=utf-8")
    public String insertConsumeNormBug(ConsumeNormDto consumeNormDto){
        Map<Object, Object> insertConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.insertConsumeNorm(consumeNormDto);
        if(i<1){
            insertConsumeNormMap.put("status","failure");
            return JSON.toJSONString(insertConsumeNormMap);
        }else{
            insertConsumeNormMap.put("status","success");
            return JSON.toJSONString(insertConsumeNormMap);
        }
    }
    @ApiOperation(value="更新管理标准bug", notes="根据传过来的设备信息来更新管理标准")
    @PostMapping(value = "/updateconsumenormbug",produces = "text/plain;charset=utf-8")
    public String updateConsumeNormBug(ConsumeNorm consumeNorm){
        Map<Object, Object> updateConsumeNormMap = new HashMap<>();
        int i = iConsumeNormService.updateConsumeNorm(consumeNorm);
        if(i<1){
            updateConsumeNormMap.put("status","failure");
            return JSON.toJSONString(updateConsumeNormMap);
        }else{
            updateConsumeNormMap.put("status","success");
            return JSON.toJSONString(updateConsumeNormMap);
        }
    }*/
}
