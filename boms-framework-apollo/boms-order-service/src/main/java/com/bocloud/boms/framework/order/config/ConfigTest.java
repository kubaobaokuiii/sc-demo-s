package com.bocloud.boms.framework.order.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.consul.serviceregistry.ConsulAutoServiceRegistrationAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: boms-framework
 * @description:
 * @author: kangxzh
 * @create: 2019-05-10 14:49
 **/
//@Configuration
//@AutoConfigureBefore(ConsulAutoServiceRegistrationAutoConfiguration.class)
@Slf4j
public class ConfigTest {

    @Value("${spring.cloud.consul.discovery.tags}")
    private String tags;

    @Bean
    public CheckConsul checkConsul() {
        log.info("check consul tags [{}]", tags);
        log.info("1/0= [{}]", 1/0);
        return new CheckConsul();
    }

    public static class CheckConsul{

    }
}
