package com.bocloud.boms.framework.controller;

import com.bocloud.boms.framework.config.Student;
import com.bocloud.boms.framework.config.UserConfig;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 配置类控制器
 * @author guo shuang
 */
@RestController
public class ConfigController {

    /**
     * Config配置类注入
     */
    @Autowired
    private UserConfig userConfig;

    /**
     * 用来注入Apollo Config对象
     */
    @ApolloConfig
    private Config config;

    /**
     * 用来把配置的JSON字符串自动注入为对象
     *  @ApolloJsonValue
     */
    @ApolloJsonValue("${stus:[{'id':1,'name': 'jason'}]}")
    private List<Student> stus;

    /**
     *   @ApolloJsonValue注解的验证
     * @return
     */
    @GetMapping("/config/stus")
    public List<Student> stus() {
        return stus;
    }

    /**
     * Placeholder注入配置的验证
     * @return
     */
    @GetMapping("/config/getUserName2")
    public String getUserName2() {
        return userConfig.getUsername();
    }

    /**
     * 注入Apollo Config对象的验证
     * @return
     */
    @GetMapping("/config/getUserName")
    public String getUserName(){

        return config.getProperty("username","guo");
    }

    /**
     * 注解方式监听配置的变化
     * @param configChangeEvent
     */
    @ApolloConfigChangeListener
    private void someOnChange(ConfigChangeEvent configChangeEvent){

        if(configChangeEvent.isChanged("username")){

            System.out.println("username发生修改了");
        }
    }


}
