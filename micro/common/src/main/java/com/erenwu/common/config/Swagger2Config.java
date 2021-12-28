package com.erenwu.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * Swagger2 配置信息
 *
 * @author eren
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Value("${swagger.enable}")
    private boolean enableSwagger;

    @Value("${swagger.title}")
    private String title;

    @Value("${swagger.description}")
    private String description;

    @Value("${swagger.version}")
    private String version;

    @Value("${swagger.basePackage}")
    private String basePackage;

    /**
     * 枚举处理类
     * @return
     */
    @Bean
    public CodedEnumPropertyPlugin codedEnumPropertyPlugin(){
        return new CodedEnumPropertyPlugin();
    }

    /**
     * 添加摘要信息(Docket)
     */
    @Bean
    public Docket authApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                //  .globalOperationParameters(parameters)
                .enable(enableSwagger)
                .apiInfo(new ApiInfoBuilder()
                        .title(title)
                        .description(description)
                        .version(version)
                        .build())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket tokenApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("token")
                //  .globalOperationParameters(parameters)
                .enable(enableSwagger)
                .apiInfo(new ApiInfoBuilder()
                        .title("token服务_接口文档")
                        .description("提供鉴权认证获取token的服务")
                        .version("v1.0")
                        .build())
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.springframework.security.oauth2.provider.endpoint"))
                .paths(PathSelectors.any())
                .build();
    }


    private List<ApiKey> securitySchemes() {
        return Collections.singletonList((
                new ApiKey("Authorization", "Authorization", "header")));
    }

    private List<SecurityContext> securityContexts() {
        return Collections.singletonList((
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        ));
    }

    /**
     * 默认认证
     *
     * @return
     */
    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList((
                new SecurityReference("Authorization", authorizationScopes)));
    }

}
