package com.ranji.lab.service;

import com.ranji.lab.entity.Menu;
import com.ranji.lab.service.prototype.IMenuService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class MenuServiceTest {


    @Resource
    private IMenuService iMenuService;

    @Test
    public void test(){
        List<Menu> rootMenu = iMenuService.findRootMenu();
        System.out.println(rootMenu);
    }
}
