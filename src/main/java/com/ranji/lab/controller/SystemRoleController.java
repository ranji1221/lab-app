package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Role;
import com.ranji.lab.service.prototype.IRoleService;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Api(tags = "系统角色管理")
@RestController
public class SystemRoleController {
    @Resource
    private IRoleService iRoleService;

    @ApiOperation(value="获取所有角色", notes="获取所有角色")
    @PostMapping(value = "/allroles",produces = "text/plain;charset=utf-8")
    public String getAllRoles(){
        List<Role> all = iRoleService.findAll();
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()) {
            allMap.put("data", all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }
        else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value="新增系统角色", notes="新增系统角色")
    @PostMapping(value = "/insertuserrole", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    public String insertUserRole(Role role){
        iRoleService.save(role);
        HashMap<Object, Object> allMap = new HashMap<>();
        if(role.getId()>0)
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }



}
