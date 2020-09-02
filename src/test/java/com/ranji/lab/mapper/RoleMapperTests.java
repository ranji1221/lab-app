package com.ranji.lab.mapper;

import com.ranji.lab.entity.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ClassName:    RoleMapper
 * Package:    com.ranji.lab.mapper
 * Description:
 * Datetime:    2020/9/1   5:03 下午
 * Author:   ranji
 */
@SpringBootTest
public class RoleMapperTests {
    @Resource
    private RoleMapper roleMapper;

    @Test
    public void testGetRightsIDS(){
        int[] rightIDS = roleMapper.getRightIDS(new String[]{"user:view","device:add"});
        System.out.println(Arrays.toString(rightIDS));
    }
    @Test
    public void testAssignRights(){
        roleMapper.assignRights(1,new int[]{1,2,3});
    }

    @Test
    public void testCancelRights(){
        roleMapper.cancelRights(1,new int[]{1,2});
    }

    @Test
    public void testGetAllAuths(){
        List<Auth> auths = roleMapper.findAuths(3);
        for (Auth auth : auths) {
            System.out.println(auth);
        }
    }
}