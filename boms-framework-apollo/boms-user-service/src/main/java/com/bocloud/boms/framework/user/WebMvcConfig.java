package com.bocloud.boms.framework.user;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置swagger静态资源
 * @author guo
 * @Create 2019/7/26
 * extends WebMvcConfigurerAdapter过期使用 implements WebMvcConfigurer 代替
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/resources/webjars/");
    }

}
