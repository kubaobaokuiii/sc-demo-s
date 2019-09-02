package com.bocloud.boms.framework.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class WebBeforeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebBeforeServiceApplication.class, args);
    }


}
