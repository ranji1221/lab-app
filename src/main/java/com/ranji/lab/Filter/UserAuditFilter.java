package com.ranji.lab.Filter;

import com.ranji.lab.service.prototype.IAuditService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

@Component
public class UserAuditFilter implements Filter {

    @Resource
    private IAuditService iAuditService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("加载拦截器");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("摧毁拦截器");
    }
}
