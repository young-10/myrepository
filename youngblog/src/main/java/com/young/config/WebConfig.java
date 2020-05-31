package com.young.config;

import com.young.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author young
 * @Description
 * @date 2020-05-01 21:40
 */
//springboot 2.x 通过实现WebMvcConfigurer的方式来配置自定义webmvc组件
@Configuration
public class WebConfig implements WebMvcConfigurer {

    //注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login");
    }

    /**
     *    扩展springmvc功能
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

    }
}
