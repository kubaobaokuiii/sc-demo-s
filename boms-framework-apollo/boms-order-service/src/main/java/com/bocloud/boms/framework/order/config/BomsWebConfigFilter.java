package com.bocloud.boms.framework.order.config;

import com.bocloud.boms.framework.order.interceptor.BomsRequestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @program: boms-framework
 * @description:
 * @author: kangxzh
 * @create: 2019-05-10 14:25
 **/
//@Configuration
public class BomsWebConfigFilter implements WebMvcConfigurer {

    @Resource
    private BomsRequestInterceptor bomsRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(bomsRequestInterceptor).addPathPatterns("/v1/agent/service/register");
    }
}
