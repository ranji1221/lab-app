/*
package com.ranji.lab.Interceptor;

import com.ranji.lab.entity.Audit;
import com.ranji.lab.service.prototype.IAuditService;
import com.ranji.lab.util.DateUtil;
import com.ranji.lab.util.GetIpAddrUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Resource
    private IAuditService iAuditService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println("过滤开始");
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //用户cookie获取username
        Cookie[] cookies = request.getCookies();
        String username = "nousername";
        */
/*for (Cookie cookie : cookies) {
            String name = cookie.getName();
            if(name.equals("name")){
                username = cookie.getName();
            }
        }*//*

        //用户ip
        String ipAddress = GetIpAddrUtil.getIPAddress(request);
        //当前时间
        String time = DateUtil.DateToString(new Date(), "yyyy-MM-dd HH-mm-ss");
        */
/*访问绝对路径接口
        String url = request.getRequestURL().toString();
        url = url.substring(url.indexOf("8080/lab")+8);*//*

        //访问相对路径接口
        String servletPath = request.getServletPath();
        System.out.println("cookies =====================================" + cookies);
        System.out.println("ipAddress =====================================" + ipAddress);
        System.out.println("time =====================================" + time);
        System.out.println("servletPath =====================================" + servletPath);
        Audit audit = new Audit(username,ipAddress,time,servletPath);
        iAuditService.insertAudit(audit);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("过滤结束");
    }
}
*/
