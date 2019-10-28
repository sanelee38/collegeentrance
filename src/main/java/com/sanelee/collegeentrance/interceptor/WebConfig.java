package com.sanelee.collegeentrance.interceptor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginHandlerInterceptor loginHandlerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List exclude = new ArrayList();//不拦截的列表
        exclude.add("/");
        exclude.add("/index.html");
        exclude.add("/front/login");
        exclude.add("/front/logout");
        exclude.add("/front/**");
        exclude.add("/css/**");
        exclude.add("/fonts/**");
        exclude.add("/js/**");
        exclude.add("/docs/**");
        exclude.add("/dist/**");
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(exclude);
    }
}
