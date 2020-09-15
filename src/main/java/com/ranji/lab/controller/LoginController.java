package com.ranji.lab.controller;

import com.alibaba.fastjson.JSONObject;
import com.ranji.lab.entity.User;
import com.ranji.lab.service.prototype.IUserService;
import com.ranji.lab.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 集成JWT的Token校验，所以登陆成功的话产生Token
 * @author RanJi
 */
@Api(tags = "系统登陆")
@RestController
public class LoginController {
    @Resource
    private IUserService userService;

    /**
     * 用户登陆的接口，若成功则会产生相对应的token
     * @param name
     * @param password
     * @param response
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "用户登陆",notes = "该接口用于系统的登陆，用户需要提交合法的用户名和密码，若登陆成功则返回合法的Token。")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataTypeClass = String.class),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataTypeClass = String.class)
    })
    @PostMapping(value = "/login",produces = "text/plain;charset=utf-8")
    public String login(String name, String password, HttpServletRequest request,HttpServletResponse response)throws Exception {
        //-- 1. 根据用户名查找用户
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        List<User> users = userService.getUsers(params);
        User user = null;
        if(users!=null && users.size()>0) user = users.get(0);
        //-- 2. 根据传过来的用户名查询是否存在该用户
        if(user == null)
            throw new RuntimeException("用户名不存在");
        else{
            if(!user.getPassword().equals(password))
                throw new RuntimeException("密码不正确");
        }
        //-- 3. 产生Token
        String token = JWTUtil.createToken(name);
        //-- 4. 准备结果
        JSONObject result = new JSONObject();
        result.put("token",token);
        //-- System.out.println(token);      //--    测试代码
        //-- 5. 返回
        return result.toJSONString();
    }
}
