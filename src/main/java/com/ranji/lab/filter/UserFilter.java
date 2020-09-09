package com.ranji.lab.filter;

import com.ranji.lab.service.prototype.IArrangeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import java.io.IOException;

@Component
public class UserFilter implements Filter {
    @Resource
    private IArrangeService iArrangeService;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("状态修改中");
        iArrangeService.changeArrangeStatus();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request,response);
        System.out.println("拦截进行中");
    }

    @Override
    public void destroy() {
        System.out.println("拦截结束");
    }
}
