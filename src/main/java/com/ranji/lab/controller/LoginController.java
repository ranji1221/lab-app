package com.ranji.lab.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ranji.lab.entity.Code;
import com.ranji.lab.entity.User;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录控制器
 * @author RanJi
 */
@Api(tags = "登陆接口")
@Controller
public class LoginController {

    /**
     * 这里必须指定保存的是text/html类型，否则不能保存Cookie
     *
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/login", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String login(String name, String password, HttpServletRequest request) throws Exception {
        //-- 1. 认证主体
        Subject subject = SecurityUtils.getSubject();
        //-- 2. 构建身份信息
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        //-- 3. 认证
        subject.login(token);
        //-- 4. 若认证通过则获取到该用户
        User user = (User) subject.getPrincipal();
        //-- 5. 返回信息(前端负责把用户的信息保存到浏览器的Cookie中，以便后面的页面中使用该信息)
        Map<String, Object> map = new HashMap<>();
        map.put("userID",user.getId());
        map.put("userName",user.getName());
        map.put(Code.SUCCESS.getMsg(),Code.SUCCESS.getCode());
        return JSON.toJSONString(map);
    }
}
