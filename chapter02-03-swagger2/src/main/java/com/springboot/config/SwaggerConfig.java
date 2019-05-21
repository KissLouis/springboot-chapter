package com.springboot.config;

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
 * @author Louis
 * @title: SwaggerConfig
 * @projectName springbootstudy
 * @description: TODO
 * @date 2019/5/21 14:30
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.springboot.controller")).paths(PathSelectors.any()).build();
    }

    /**
     * @param []
     * @return springfox.documentation.service.ApiInfo
     * @description: //TODO 构建api文档的详细信息函数
     * @author Louis
     * @date 2019/5/21 14:40
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Spring Boot使用Swagger2构建RESTful API").contact(new Contact("Louis", "", "")).version("1.0").description("RESTful API 风格接口").build();
    }

}
