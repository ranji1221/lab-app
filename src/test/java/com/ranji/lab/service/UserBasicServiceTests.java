package com.ranji.lab.service;

import com.ranji.lab.dto.UserBasicDto;
import com.ranji.lab.service.prototype.IUserBasicService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserBasicServiceTests {

    @Resource
    private IUserBasicService iUserBasicService;


    @Test
    public void testUserBasicSelect(){
        UserBasicDto userBasic = iUserBasicService.findUserBasic(1);
        System.out.println(userBasic);
    }
}
