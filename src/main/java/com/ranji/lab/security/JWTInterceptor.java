package com.ranji.lab.security;

import com.ranji.lab.service.prototype.IUserService;
import com.ranji.lab.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName:    AuthInterceptor
 * Package:    com.ranji.lab.security
 * Description: JWT认证拦截器
 * Datetime:    2020/9/10   11:08 上午
 * Author:   ranji
 */
public class JWTInterceptor implements HandlerInterceptor {
    @Resource
    private IUserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //-- 跨域请求时，前端会首先发一个option请求，这里我们对option请求直接返回正常状态即可
        //-- 1. 如果是OPTIONS请求则，直接返回true
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            response.setStatus(HttpStatus.OK.value());
            return true;
        }

        //-- 2. 从请求的header中获取token
        String token = request.getHeader("token");  // 从http请求头中取出token
        if(token == null) {
            throw new RuntimeException("please, apply token by login.");
        }

        //-- 3. 通过获取的token，解析里面包含的用户名
        String userName = JWTUtil.getUsername(token);
        System.out.println(userName);

        //-- 4. 认证
        Subject subject = SecurityUtils.getSubject();
        //-- 准备好自己定义的认证实体（不是利用用户名和密码，而是token进行认证）
        JWTToken jwtToken = new JWTToken(token);
        //-- 交给shiro框架进行认证，它会调用自定义的SystemRealm的doGetAuthenticationInfo认证方法
        subject.login(jwtToken);

        return true;
    }
}