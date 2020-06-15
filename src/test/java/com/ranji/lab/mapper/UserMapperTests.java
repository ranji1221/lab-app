package com.ranji.lab.mapper;

import com.github.pagehelper.PageHelper;
import com.ranji.lab.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.List;

/**
 * UserMapper单元测试类
 */
@SpringBootTest
public class UserMapperTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSave(){
        User u = new User("wang","123456",1);
        userMapper.save(u);
    }

    @Test
    public void testFind(){
        PageHelper.startPage(1,2);
        List<User> users = userMapper.findAll();
        for (User u : users) {
            System.out.println(u);
        }
    }
}
