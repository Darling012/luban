package com.cntytz.yunti.config;

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
 * @author Darling
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 返回实例例 Docket（Swagger API 摘要），这⾥里里需要注意的是
        // .apis(RequestHandlerSelectors.basePackage("xxx.xxx.xxx")) 指定需要扫描的包路路径
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 需要提供API的包路径
                .apis(RequestHandlerSelectors.basePackage("com.cntytz.project.demo.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("架构-工具 样例代码")
                .description("本项目用于当前开发改善DEMO")
                .contact(
                        new Contact("Darling", "https://github.com/DarLing012", "7827756@qq.com")
                        )
                .version("1.0.0")
                .build();
    }
}
