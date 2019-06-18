package com.hunqingplatform.hunqing.config.swagger;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
   * 功能描述：Swagger2Config 配置功能模块
   * @Author corbett
   * @Description //TODO
   * @Date 15:37 2018/10/19
   **/
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class Swagger2Config {
    @Value("${swagger2.enable}") private boolean enable;

    
    @Bean("前端模块")
    public Docket userApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("前台接口模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hunqingplatform.hunqing.controller.front"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    
    @Bean("内容管理模块")
    public Docket projectApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("内容模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hunqingplatform.hunqing.controller.project"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }
    
    @Bean("系统模块")
    public Docket actApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("系统模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hunqingplatform.hunqing.controller.system"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }
    
    @Bean("后端使用的测试模块")
    public Docket testApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后端使用的测试模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hunqingplatform.hunqing.controller.test"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }
    
    @Bean("用户管理模块")
    public Docket weixApis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("用户管理模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hunqingplatform.hunqing.controller.user"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }

    @Bean("公共模块")
    public Docket h5Apis() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("公共模块")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hunqingplatform.hunqing.controller.common"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .enable(enable);
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("婚庆系统接口文档")
                .description("")
                .termsOfServiceUrl("")
                .version("0.1")
                .build();
    }
    
}