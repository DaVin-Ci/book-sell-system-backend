package com.example.demo.Config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 这个配置不用去记，开发的时候只用配置一次即可

@Configurable // 告诉Spring容器，该类为一个配置类
@EnableSwagger2 // 启用2的功能
public class SwaggerConfig {
    // 配置Swagger2相关的bean
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("该文档包含了书店销售管理系统项目中所有的API信息"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com")) // 告诉它扫描哪个包(根目录)，把com包下的所有API都交给Swagger2管理，它会自动读取其中的控制器
                .paths(PathSelectors.any()).build();
    }
    // API文档页面显示信息
    private ApiInfo apiInfo(String desc) {
        return new ApiInfoBuilder()
                .title("书店销售管理系统API")
                .description(desc)
                .version("1.1")
                .build();
    }
}
