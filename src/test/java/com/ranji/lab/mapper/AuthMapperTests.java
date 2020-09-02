package com.ranji.lab.mapper;

import java.util.List;

import com.ranji.lab.entity.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

/**
 * ClassName:    AuthMapperTests
 * Package:    com.ranji.lab.mapper
 * Description:
 * Datetime:    2020/9/1   3:25 下午
 * Author:   ranji
 */
@SpringBootTest
public class AuthMapperTests {
    @Resource
    private AuthMapper authMapper;

    /**
     * 查询权限所对应的所有的类型
     */
    @Test
    public void testFindTypes(){
        List<String> types = authMapper.findAuthTypes();
        for (String type : types) {
            System.out.println(type);
        }
    }

    /**
     * 查询某中类型的权限
     */
    @Test
    public void testFindAuths(){
        List<Auth> auths = authMapper.findAuths("用户管理");
        for (Auth auth : auths) {
            System.out.println(auth);
        }
    }
}