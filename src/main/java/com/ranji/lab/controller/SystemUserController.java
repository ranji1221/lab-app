package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.User;
import com.ranji.lab.service.prototype.IRoleService;
import com.ranji.lab.service.prototype.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "系统用户管理")
@RestController
public class SystemUserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IRoleService iRoleService;

    @ApiOperation(value="添加用户账号信息", notes="添加角色信息")
    @PostMapping(value = "/insertuser",produces = "text/plain;charset=utf-8")
    public String insertUser(User user , int roleId){
        user.setEnable(1);
        int userId = iUserService.save(user);
        iUserService.assignRole(userId , roleId);
        HashMap<Object, Object> allMap = new HashMap<>();
        if(userId>0)
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value="分页查找所有账号信息", notes="分页查找所有账号信息")
    @PostMapping(value = "/alluser",produces = "text/plain;charset=utf-8")
    public String user(int page , int limit){
        Map<Object, Object> allUsers = iUserService.getAllUsers(page, limit);
        return JSON.toJSONString(allUsers);
    }
}
