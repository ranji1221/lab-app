package com.ranji.lab.service;

import com.ranji.lab.entity.Role;
import com.ranji.lab.service.prototype.IRoleService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ClassName:    RoleServiceTests
 * Package:    com.ranji.lab.service
 * Description:
 * Datetime:    2020/8/31   6:10 下午
 * Author:   ranji
 */
@SpringBootTest
public class RoleServiceTests {
    @Resource
    private IRoleService roleService;

    @Test
    public void testAdd(){
       Role role = new Role("aaa","市场人员");
       roleService.save(role);
    }

    @Test
    public void testAssignRights(){
        //roleService.assignAuths(1,new String[]{"user:view","device:add"});
        roleService.assignAuths(2,new int[]{1,2,4});
    }

    @Test
    public void testCancelRights(){
        roleService.cancelAuths(1,new String[]{"user:view","device:add"});
    }
}