package com.erenwu.basic.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: erenwu
 */
@RestController
@RequestMapping("/basic")
public class BasicController {

    @Value("${java123}")
    private String java;

    @SentinelResource("testResource")
    @GetMapping("/get")
    public String getConfig() {
        return "basic服务java配置-" + java;
    }
}
