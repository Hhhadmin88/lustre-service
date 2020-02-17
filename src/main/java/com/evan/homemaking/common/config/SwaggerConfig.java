package com.evan.homemaking.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Evaluation
 * @Description Visit http://localhost:8090/swagger-ui.html You can get detailed interface documents.
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2019/12/3 14:20
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(buildApiInf())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.evan.homemaking.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
                .title("家政服务管理系统")
                .description("接口文档")
                .contact(new Contact("Evan Wang", "http://evanwang.blog.csdn.net/", "wangmingmis@163.com"))
                .version("1.0.0")
                .build();
    }

}