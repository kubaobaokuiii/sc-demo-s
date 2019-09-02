package com.bocloud.boms.framework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Apollo整合spring boot
 * @author guo shuang
 *
 */
@SpringBootApplication
public class ApolloSpringBootApplication {

    public static void main(String[] args){

        //指定环境(不能用于生产环境)
        System.setProperty("env","DEV");

        SpringApplication.run(ApolloSpringBootApplication.class,args);
    }
}
