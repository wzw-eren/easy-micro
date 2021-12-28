package com.erenwu.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * WebSocket 配置文件
 *
 * @author erenwu
 */
@Data
@Component
@ConfigurationProperties(prefix = "erenwu.gateway")
public class ErenGatewayProperties {


    /**
     * 固定无需鉴权的uri
     * /
     * /favicon.ico
     * /swagger-ui.html
     * /csrf
     * /swagger-resources/**
     * /webjars/**
     * /auth/oauth/token
     *
     */
    private String[] noAuthUris;

    /**
     * 无权访问返回的错误码
     */
    private String accessDeniedCode = "A0301";


    /**
     * 认证失败返回的错误码
     */
    private String authFailCode = "A0302";

    /**
     * 系统异常返回的错误码
     */
    private String systemErrCode = "E0500";


    /**
     * 认证通过后 后续的用户token 放入请求头的名字
     */
    private String userTokenHeader = "X-Request-Token";

    /**
     * 是否使用xss过滤器
     */
    private Boolean useXssFilter = false;
    /**
     * 是否使用Swagger
     */
    private Boolean useSwagger = false;


}
