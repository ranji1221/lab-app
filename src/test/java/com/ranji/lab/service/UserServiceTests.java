package com.ranji.lab.service;

import com.ranji.lab.entity.Auth;
import com.ranji.lab.entity.Role;
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
        User u = new User("kai","hallo",1);
        userService.save(u);
        System.out.println(u.getId());
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

    @Test
    public void testAssignRole(){
        userService.assignRole(3,1);
    }

    @Test
    public void testGetRole(){
        Role role = userService.getRole(5);
        System.out.println(role);
    }

    @Test
    public void testGetRole2(){
        Role role = userService.getRole("kai");
        System.out.println(role);
    }

    @Test
    public void testAssignRoles(){
        userService.assignRoles(1,new int[]{1,2});
    }

    @Test
    public void testFindRoles(){
        List<Role> roles = userService.getRoles("zhangsan");
        for (Role role : roles) {
            System.out.println(role);
        }
    }

    @Test
    public void testFindAuths(){
        List<String> auths = userService.getAuthsCode("lisi");
        for (String auth : auths) {
            System.out.println(auth);
        }
    }

    @Test
    public void testFindAuths2(){
        List<Auth> auths = userService.getAuths("lisi");
        for (Auth auth : auths) {
            System.out.println(auth);
        }
    }
}
