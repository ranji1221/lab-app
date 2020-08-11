package com.ranji.lab.config;

import com.ranji.lab.security.SystemRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.lettuce.core.protocol.CommandKeyword.BY;

/**
 * Shiro安全框架配置
 */
@Configuration
public class ShiroConfig {

    @Bean(name="shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //-- 1. 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //-- 2. 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //-- 3. 准备拦截参数
        // 这里需要LinkedHashMap 不能HashMap （会出现代码已经配置却依然无权限访问的问题）
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        filterMap.put("/css/**","anon");
        filterMap.put("/image/**","anon");
        filterMap.put("/js/**","anon");
        filterMap.put("/login","anon");
        filterMap.put("/logout","logout");  //配置退出过滤器,其中的具体的退出代码Shiro已经实现
        //filterMap.put("/**","authc");     //暂时注释掉权限认证
        filterMap.put("/**","anon");
        //-- 4. 设置拦截参数
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     */
    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(SystemRealm systemRealm){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setRealm(systemRealm);
        return securityManager;
    }

    /**
     * 开启shiro aop注解支持.(使用代理方式，所以需要开启代码支持)
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
    @Bean(name = "defaultAdvisorAutoProxyCreator")
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }
    @Bean
    public AuthorizationAttributeSourceAdvisor authorization(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 将自己的验证方式加入容器
     */
    @Bean(name="selfShiroRealm")
    public SystemRealm myShiroRealm(){
        SystemRealm myShiroRealm = new SystemRealm();
        return myShiroRealm;
    }
}
