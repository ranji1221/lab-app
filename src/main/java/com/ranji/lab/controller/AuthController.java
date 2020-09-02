package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ranji.lab.entity.Auth;
import com.ranji.lab.entity.Role;
import com.ranji.lab.entity.User;
import com.ranji.lab.entity.UserDTO;
import com.ranji.lab.service.prototype.IAuthService;

import java.util.*;

import com.ranji.lab.service.prototype.IRoleService;
import com.ranji.lab.service.prototype.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * ClassName:    AuthController
 * Package:    com.ranji.lab.controller
 * Description: 权限控制器
 * Datetime:    2020/9/1   4:08 下午
 * Author:   ranji
 */
@Api(tags = "权限管理")
@RestController
public class AuthController {
    @Resource
    private IAuthService authService;
    @Resource
    private IRoleService roleService;
    @Resource
    private IUserService userService;

    /**
     * 获取系统中存在的所有权限信息api
     * @return
     */
    @ApiOperation(value="获取所有权限", notes="该接口获取到系统中所有的权限信息（按照分类给定所有的权限，方便前端做展示）")
    @GetMapping(value = "/auths",produces = "text/plain;charset=utf-8")
    public String getAuths(){
        return JSON.toJSONString(authService.getAllAuths());
    }

    /**
     * 获取某人的所拥有的权限信息api
     * @param userName
     * @return
     */
    @ApiOperation(value="获取某人的所有权限", notes="该接口通过用户的登陆名获取到该用户的所有权限（按照分类给定所有的权限，方便前端做展示）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String")
    })
    @GetMapping(value = "/authssomeone",produces = "text/plain;charset=utf-8")
    public String getAuths(String userName){
        //-- 1. 获取某人的所有权限
        List<Auth> auths = authService.getAllAuths(userName);
        //-- 2. 为方便前端做展示，重新构造JSON字符串(按权限类型分类)
        //-- 构造JSON对象
        JSONObject jo = new JSONObject();
        //-- 添加名字
        jo.put("name",userName);
        //-- 获取所有的权限类型，利用Set的不重复属性
        Set<String> types = new HashSet<String>();
        for (Auth auth : auths) {
            types.add(auth.getType());
        }
        // -- 构造List对象
        List<Map> data = new ArrayList<Map>();
        //-- 构造每种类型所对应的权限
        for (String type : types) {
           Map<String,Object> map = new HashMap<String,Object>();
           map.put("type",type);
           List<Auth> as = new ArrayList<Auth>();
           for (Auth auth : auths) {
                if(auth.getType().equals(type)){
                    as.add(auth);
               }
           }
           map.put("rights",as);
           data.add(map);
        }
        //-- 添加data
        jo.put("data",data);
        return jo.toJSONString();
    }

    @ApiOperation(value="获取所有角色", notes="该接口获取到系统所拥有的角色信息")
    @GetMapping(value = "/roles",produces = "text/plain;charset=utf-8")
    public String getRoles(){
        List<Role> roles = roleService.findAll();
        return JSON.toJSONString(roles);
    }

    /*
    //-- 该接口暂时不用，用/adduserandassignrole接口即可
    @ApiOperation(value="给某人分配某个角色", notes="该接口通过用户ID和角色ID,实现给某个用户分配某个角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userID", value = "用户ID", required = true),
            @ApiImplicitParam(name = "roleID", value = "角色ID", required = true)
    })
    @PostMapping(value = "/assignrole", produces = "text/plain;charset=utf-8")
    public String assignRole(int userID, int roleID){
        userService.assignRole(userID,roleID);
        JSONObject jo = new JSONObject();
        jo.put("assign","success");
        return jo.toJSONString();
    }*/

    @ApiOperation(value="给某角色分配某项权限", notes="该接口主要通过角色ID和权限ID,实现给某角色分配某项权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleID", value = "角色ID", required = true),
            @ApiImplicitParam(name = "authID", value = "权限ID", required = true)
    })
    @PostMapping(value = "/assignauth", produces = "text/plain;charset=utf-8")
    public String assignAuth(int roleID, int authID){
        roleService.assignAuths(roleID,new int[]{authID});
        JSONObject jo = new JSONObject();
        jo.put("assign","success");
        return jo.toJSONString();
    }

    @ApiOperation(value="解除某角色分配某项权限", notes="该接口主要通过角色ID和权限ID,实现解除某角色的某项权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleID", value = "角色ID", required = true),
            @ApiImplicitParam(name = "authID", value = "权限ID", required = true)
    })
    @PostMapping(value = "/cancelauth", produces = "text/plain;charset=utf-8")
    public String cancelAuth(int roleID, int authID){
        roleService.cancelAuths(roleID,new int[]{authID});
        JSONObject jo = new JSONObject();
        jo.put("assign","success");
        return jo.toJSONString();
    }

    @ApiOperation(value="增加用户并且给用户分配角色", notes="该接口主要通过用户信息和角色ID,实现增加用户并给用户分配角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true),
            @ApiImplicitParam(name = "password", value = "密码", required = true),
            @ApiImplicitParam(name = "roleID", value = "角色ID", required = true)
    })
    @PostMapping(value="/adduserandassignrole",produces = "text/plain;charset=utf-8")
    public String assignRole(UserDTO userdto, int roleID){
        System.out.println(userdto.getName()+":"+userdto.getPassword());
        //-- 1. 利用UserDTO对象构造User对象
        User user = new User(userdto.getName().trim(),userdto.getPassword().trim(),1);
        //-- 2. 添加用户
        userService.save(user);
        //-- 3. 分配角色
        userService.assignRole(user.getId(),roleID);
        //-- 4. 返回信息
        JSONObject jo = new JSONObject();
        jo.put("add","success");
        jo.put("assign","success");
        return jo.toJSONString();
    }
}