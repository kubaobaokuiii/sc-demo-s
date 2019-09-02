package com.bocloud.boms.framework.gateway;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 是spring cloud启动注解
 */
@EnableApolloConfig
//@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args){

        SpringApplication.run(GatewayApplication.class,args);
    }
}
