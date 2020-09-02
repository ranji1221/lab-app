package com.ranji.lab.service.impl;

import com.alibaba.fastjson.JSON;
import com.ranji.lab.entity.Auth;
import com.ranji.lab.mapper.AuthMapper;
import com.ranji.lab.service.prototype.IAuthService;
import com.ranji.lab.service.prototype.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ClassName:    AuthServiceImpl
 * Package:    com.ranji.lab.service.impl
 * Description: 权限业务实现类
 * Datetime:    2020/9/1   3:43 下午
 * Author:   ranji
 */
@Service
public class AuthServiceImpl implements IAuthService {
    @Resource
    private AuthMapper authMapper;
    @Resource
    private IUserService userService;

    @Override
    public List<Map<String, Object>> getAllAuths() {
        //-- 1. 查询权限所有的类别
        List<String> types = authMapper.findAuthTypes();
        //-- 2. 定义返回的结果字符串
        ArrayList<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        //-- 3. 按照分类构建权限数据
        for (String type : types) {
            HashMap<String, Object> map = new HashMap<String,Object>();
            map.put("type",type);
            List<Auth> auths = authMapper.findAuths(type);
            map.put("data",auths);
            result.add(map);
        }
        //System.out.println(JSON.toJSONString(result));
        return result;
    }

    @Override
    public List<Auth> getAllAuths(String userName){
        return userService.getAuths(userName);
    }
}