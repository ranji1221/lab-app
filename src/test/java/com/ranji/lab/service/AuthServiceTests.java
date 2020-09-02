package com.ranji.lab.service;

import com.ranji.lab.entity.Auth;
import com.ranji.lab.service.prototype.IAuthService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName:    AuthServiceTests
 * Package:    com.ranji.lab.service
 * Description:
 * Datetime:    2020/9/1   3:56 下午
 * Author:   ranji
 */
@SpringBootTest
public class AuthServiceTests {
    @Resource
    private IAuthService authService;

    @Test
    public void testFindAll(){
        authService.getAllAuths();
    }

    @Test
    public void testFindAuths(){
        List<Auth> auths = authService.getAllAuths("lisi");
        for (Auth auth : auths) {
            System.out.println(auth);
        }
    }
}