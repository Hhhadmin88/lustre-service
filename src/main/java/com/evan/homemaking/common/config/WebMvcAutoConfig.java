package com.evan.homemaking.common.config;

import com.evan.homemaking.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcAutoConfiguration
 * @Description Mvc configuration.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/5 12:43
 */
@Slf4j
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.evan.homemaking.controller")
public class WebMvcAutoConfig implements WebMvcConfigurer {

    @Autowired
    LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/error");
    }
}
