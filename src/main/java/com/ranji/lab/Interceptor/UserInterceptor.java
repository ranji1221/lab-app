package com.ranji.lab.Interceptor;

import com.ranji.lab.entity.Audit;
import com.ranji.lab.entity.User;
import com.ranji.lab.service.prototype.IArrangeService;
import com.ranji.lab.service.prototype.IAuditService;
import com.ranji.lab.service.prototype.IDeviceService;
import com.ranji.lab.service.prototype.IUserService;
import com.ranji.lab.util.DateUtil;
import com.ranji.lab.util.GetIpAddrUtil;
import io.swagger.models.HttpMethod;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInterceptor implements HandlerInterceptor {

    @Resource
    private IUserService userService;
    @Resource
    private IAuditService iAuditService;
    @Resource
    private IArrangeService iArrangeService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String method = request.getMethod();    //输出 OPTIONS/GET/POST。。。
        //如果是 OPTIONS 请求，让 OPTIONS 请求返回一个200状态码
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        //String username = "nousername";
        String username = request.getHeader("name");
        System.out.println("name" + username);
        if (username == null || "".equals(username))
            return true;

        //-- 3. 认证
        Subject subject = SecurityUtils.getSubject();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", username);
        List<User> users = userService.getUsers(params);
        User user = null;
        if (users != null && users.size() > 0) user = users.get(0);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getName(), user.getPassword());
        //-- 3. 认证
        subject.login(usernamePasswordToken);

        //-- 4. 保持日志
        //用户ip
        String ipAddress = GetIpAddrUtil.getIPAddress(request);
        //当前时间
        String time = DateUtil.DateToString(new Date(), "yyyy-MM-dd HH-mm-ss");

        //访问相对路径接口
        String servletPath = request.getServletPath();

        //System.out.println("cookies =====================================" + cookies);
        /*System.out.println("ipAddress =====================================" + ipAddress);
        System.out.println("time =====================================" + time);
        System.out.println("servletPath =====================================" + servletPath);
        System.out.println("username =====================================" + username);*/

        Audit audit = new Audit(username, ipAddress, time, servletPath);
        iAuditService.insertAudit(audit);
        iArrangeService.changeArrangeStatus();

        return true;
    }

}
