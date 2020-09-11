package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.UserBasicDto;
import com.ranji.lab.dto.UserDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.User;
import com.ranji.lab.entity.UserBasic;
import com.ranji.lab.service.prototype.IRoleService;
import com.ranji.lab.service.prototype.IUserBasicService;
import com.ranji.lab.service.prototype.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "系统用户管理")
@RestController
public class SystemUserController {
    @Resource
    private IUserService iUserService;
    @Resource
    private IRoleService iRoleService;
    @Resource
    private IUserBasicService iUserBasicService;

    @ApiOperation(value = "添加用户账号信息", notes = "添加角色信息")
    @PostMapping(value = "/insertuser", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
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

    @ApiOperation(value = "分页查找所有账号信息", notes = "分页查找所有账号信息")
    @GetMapping(value = "/alluser", produces = "text/plain;charset=utf-8")
    public String user(int page , int limit){
        Map<Object, Object> allUsers = iUserService.getAllUsers(page, limit);
        return JSON.toJSONString(allUsers);
    }


    @ApiOperation(value = "修改用户",notes = "根据给定的User对象进行信息修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true , dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true , dataType = "String"),
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true , dataType = "String")
    })
    @RequestMapping(value="/updateUser",method = RequestMethod.POST)
    public String updateUser(User u,String oldPassword){
        Subject subject = SecurityUtils.getSubject();
        //-- 2. 构建身份信息
        UsernamePasswordToken token = new UsernamePasswordToken(u.getName(),oldPassword);
        //-- 3. 认证
        subject.login(token);
        //-- 4. 若认证通过则获取到该用户,说明旧密码正确
        User user = (User) subject.getPrincipal();
        Map<String,Object> map = new HashMap<>();
        if(user.getId()>0){
            iUserService.updateUser(u);
            map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else{
            map.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        }
        return JSON.toJSONString(map);
    }


    @ApiOperation(value = "获取所有教师角色用户", notes = "获取所有教师角色用户")
    @GetMapping(value = "/allteachers", produces = "text/plain;charset=utf-8")
    public String allTeachers(){
        List<UserDto> all = iUserService.findAllTeachers();
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()) {
            allMap.put("data", all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "获取所有学生角色用户", notes = "获取所有学生角色用户")
    @GetMapping(value = "/allstudents", produces = "text/plain;charset=utf-8")
    public String allStudents(){
        List<UserDto> all = iUserService.findAllStudents();
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!all.isEmpty()) {
            allMap.put("data", all);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value = "通过用户id获取某用户基本资料", notes = "通过用户id获取某用户基本资料")
    @GetMapping(value = "finduserbasic", produces = "text/plain;charset=utf-8")
    public String findUserBasicByname(int id){
        UserBasicDto userBasic = iUserBasicService.findUserBasic(id);
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!(userBasic==null)) {
            allMap.put("data", userBasic);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }


    @ApiOperation(value="插入某用户基本资料", notes="插入某用户基本资料")
    @PostMapping(value = "insertuserbasic",produces = "text/plain;charset=utf-8")
    public String insertuserbasic(UserBasic userBasic){
        int userId = iUserBasicService.insertOrUpdateUserBasic(userBasic);
        HashMap<Object, Object> allMap = new HashMap<>();
        if(userId>0) {
            allMap.put("data", userBasic);
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }


}
