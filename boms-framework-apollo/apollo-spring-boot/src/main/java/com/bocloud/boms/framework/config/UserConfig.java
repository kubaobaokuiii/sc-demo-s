package com.bocloud.boms.framework.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义Apollo配置类
 * @author guo shuang
 * Java Config使用方式
 */
@Data
@Configuration
public class UserConfig {

    /**
     * Placeholder注入配置
     */
    @Value("${username:guo}")
    private String username;

}
