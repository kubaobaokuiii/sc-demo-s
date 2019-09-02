package com.bocloud.boms.framework.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 集成swager
 * @author guo
 * @create 2019/7/30
 */
@Configuration
public class Swagger2 {

    public Swagger2() {

    }

    @Bean
    public Docket createRestApi() {
        return (new Docket(DocumentationType.SWAGGER_2))
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bocloud.boms.framework.order.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder())
                .title("博云api文档")
                .description("博云云平台")
                .termsOfServiceUrl("http://www.bocloud.com")
                .version("1.0").build();
    }

}
