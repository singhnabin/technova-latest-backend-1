package com.pawan.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.pawan.ecommerce"))
                .paths(PathSelectors.ant("/api/**"))
                .build();
    }


    private ApiInfo apiDetails(){
        return new ApiInfo(
                "Ecommerce API",
                "API for Techniva Ecommerce Webesite",
                "1.0",
                "Free to use",
               "jbdchjd",
                "API License",
                "http://technova.io");

    }


}
