package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.dto.MenuDto;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.Menu;
import com.ranji.lab.service.prototype.IMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "系统菜单管理")
@RestController
public class SystemMenuController {
    @Resource
    private IMenuService iMenuService;

    @ApiOperation(value="更新菜单", notes="更新菜单")
    @PostMapping(value = "/updatemenu", produces = "text/plain;charset=utf-8")
    @RequiresRoles(value = {"admin"}, logical = Logical.OR)
    public String updateMenu(String menus){
        int i = iMenuService.updateMenuService(menus);
        HashMap<Object, Object> allMap = new HashMap<>();
        if(i<1)
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        else
            allMap.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value="获取根菜单的所有菜单信息", notes="获取根菜单的所有菜单信息")
    @GetMapping(value = "/allrootmenus",produces = "text/plain;charset=utf-8")
    public String allRootMenus(){
        List<Menu> rootMenu = iMenuService.findRootMenu();
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!rootMenu.isEmpty()){
            allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            allMap.put("data", rootMenu);
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }

    @ApiOperation(value="根据根菜单id的所有子菜单信息", notes="根据根菜单id的所有子菜单信息")
    @GetMapping(value = "/allsonmenus",produces = "text/plain;charset=utf-8")
    public String allSonMenus(int id){
        List<MenuDto> rootMenu = iMenuService.findSonMenuByRootMenuId(id);
        HashMap<Object, Object> allMap = new HashMap<>();
        if(!rootMenu.isEmpty()){
            allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
            allMap.put("data", rootMenu);
        }else
            allMap.put(Code.FAILURE.getMsg(),Code.FAILURE.getCode());
        return JSON.toJSONString(allMap);
    }

    //查询所有菜单
    @ApiOperation(value="根据根所有菜单信息", notes="查询所有菜单信息")
    @GetMapping(value = "/findAllMenu",produces = "text/plain;charset=utf-8")
    public String findAllMenu(){
        Map<String, Object> allMap = iMenuService.findAllMenu();
        if(!allMap.isEmpty()){
            allMap.put(Code.SUCCESS.getMsg(), Code.SUCCESS.getCode());
        }else {
            allMap.put(Code.FAILURE.getMsg(), Code.FAILURE.getCode());
        }
        return JSON.toJSONString(allMap);
    }

}
