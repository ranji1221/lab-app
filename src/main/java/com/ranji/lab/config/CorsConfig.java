package com.ranji.lab.config;

import com.ranji.lab.Interceptor.UserInterceptor;
import com.ranji.lab.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

/**
 *  全局跨域配置
 *  注意：全局跨域配置后会触发两个问题
 *  1、它会打破SpringBoot的web自动配置，导致静态资源访问不到，所以在这里要自己进行静态资源路径映射
 *  2、它会导致Swagger接口文档无法访问，被拦截，所以要自行处理下接口文档不能访问的问题
 *  3、如果不是全部的接口需要跨域，那么还是建议采用第一种跨域的解决方案，利用@CrossOrigin注解标注即可
 *     就无需配置这个配置类，该注解可以标注类或者防范，做到精细化的控制接口是否需要跨域
 *  @author RanJi
 *  @since jdk8
 */
@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    /**
     * 自定义拦截器实体
     */
    @Resource
    private UserInterceptor userInterceptor;

    /**
     * 全局跨域处理
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(3600);
    }

    /**
     * 一旦配置了跨域问题，就会涉及到静态资源的访问处理
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        //-- 第一种映射静态资源的方法
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/resources/")
                .addResourceLocations("classpath:/static/")
                .addResourceLocations("classpath:/public/")
        ;


        //-- 处理Swagger接口文档无法访问的问题
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");

        super.addResourceHandlers(registry);
    }

    /**
     * 自定义拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(setUserInterceptor()).addPathPatterns("/**").excludePathPatterns("/login");
        super.addInterceptors(registry);
    }

    @Bean
    public UserInterceptor setUserInterceptor() {
        return new UserInterceptor();
    }

}
