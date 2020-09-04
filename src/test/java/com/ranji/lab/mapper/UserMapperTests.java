package com.ranji.lab.mapper;

import com.github.pagehelper.PageHelper;
import com.ranji.lab.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserMapper单元测试类
 * @author RanJi
 */
@SpringBootTest
public class UserMapperTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSave(){
        /*User u = new User("wang","123456",1);
        userMapper.save(u);*/
    }

    @Test
    public void testFind(){
        PageHelper.startPage(1,2);
        List<User> users = userMapper.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindByParams(){
        //-- 1. 准备参数
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name","li");
        params.put("enable",1);
        //-- 2. 查询
        List<User> users = userMapper.find(params);
        for (User user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(4);
        user.setPassword("1111");
        userMapper.updateUser(user);
    }

}
