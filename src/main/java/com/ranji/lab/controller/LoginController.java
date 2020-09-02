package com.ranji.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.ranji.lab.entity.User;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器，为了适应前后台分离开发，则需把先前的代码注释起来
 * @author RanJi
 */
@Api(tags = "系统登陆")
@RestController
public class LoginController {
    /*
    //-- 第一种解决跨域问题的解决方案，直接在某个要访问的接口路径上加@CrossOrigin注解
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
    }*/

    /**
     * 这里必须指定保存的是text/html类型，否则不能保存Cookie
     * @param name
     * @param password
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(String name, String password, HttpServletResponse response)throws Exception {
        //-- 1. 认证主体
        Subject subject = SecurityUtils.getSubject();
        //-- 2. 构建身份信息
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //-- 3. 认证
        subject.login(token);
        //-- 4. 若认证通过则获取到该用户
        User user = (User) subject.getPrincipal();
        //-- 5. 返回信息(前端负责把用户的信息保存到浏览器的Cookie中，以便后面的页面中使用该信息)
        JSONObject result = new JSONObject();
        result.put("userID",user.getId());
        result.put("userName",user.getName());
        return result.toJSONString();
    }
}
