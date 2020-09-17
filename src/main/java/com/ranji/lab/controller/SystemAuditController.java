package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.service.prototype.IAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "系统用户审计管理")
@RestController
public class SystemAuditController {

    @Resource
    private IAuditService iAuditService;

    @ApiOperation(value = "分页查找用户审计", notes = "分页查找用户审计")
    @GetMapping(value = "/allauditpaging", produces = "text/plain;charset=utf-8")
    public String allAuditPaging(int page,int limit){
        Map<Object, Object> all = iAuditService.findAll(page, limit);
        return JSON.toJSONString(all);
    }

    @ApiOperation(value = "查找用户审计", notes = "查找用户审计")
    @GetMapping(value = "/allaudit", produces = "text/plain;charset=utf-8")
    public String allAudit(){
        Map<Object, Object> all = iAuditService.findAll();
        return JSON.toJSONString(all);
    }

}
