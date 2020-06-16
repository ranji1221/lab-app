package com.ranji.lab.service;

import com.ranji.lab.entity.User;
import com.ranji.lab.service.prototype.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserService单元测试类
 * @author RanJi
 */
@SpringBootTest
public class UserServiceTests {
    @Resource
    private IUserService userService;

    @Test
    public void testSave(){
        userService.save(new User("kai","hallo",1));
    }

    @Test
    public void testFind(){
        List<User> users = userService.getAllUsers();
        for (User u: users) {
            System.out.println(u);
        }
    }

    @Test
    public void testFindByParams(){
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("enable",1);
        List<User> users = userService.getUsers(params);
        for (User user : users) {
            System.out.println(user);
        }
    }
}
