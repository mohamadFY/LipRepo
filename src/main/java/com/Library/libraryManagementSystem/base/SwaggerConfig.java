//package com.Library.libraryManagementSystem.base;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.Collections;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    @Bean
//    public Docket info(){
//        return new Docket(DocumentationType.SWAGGER_2).
//                select().
//                apis(RequestHandlerSelectors.basePackage("com.Library.libraryManagementSystem")).build().
//                apiInfo(apiInfo());
//    }
//
//    private ApiInfo apiInfo(){
//        ApiInfo apiInfo = new ApiInfo(
//                "LIBRARY MANAGEMENT SYSTEM",
//                "LIBRARY BACK END PROVIDED BY SPRING BOOT",
//                "Version",
//                "Term",
//                new Contact("Mhd Mazhar", "www.example", "m.m.sy42@gmail.com"),
//                "License of API",
//                "API License URL",
//                Collections.emptyList()
//        );
//        return apiInfo;
//    }
//}
