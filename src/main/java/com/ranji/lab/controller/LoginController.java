package com.ranji.lab.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 登录控制器
 * @author RanJi
 */
@Controller
public class LoginController {

    /**
     * 第一种解决跨域问题的解决方案，直接在某个要访问的接口路径上加@CrossOrigin注解
     * @return
     */
    //@CrossOrigin
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String toLogin(){
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(
            @RequestParam("name") String name, @RequestParam("password") String password){
        //-- 1. 认证主体
        Subject subject = SecurityUtils.getSubject();
        //-- 2. 构建身份信息
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //-- 3. 认证
        subject.login(token);
        return "redirect:/index";
    }
}
