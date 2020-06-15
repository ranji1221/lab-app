package com.ranji.lab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Test控制器
 */
@Controller
public class HelloController {
    @RequestMapping("/index")
    public String test(){
        return "index";
    }
}
