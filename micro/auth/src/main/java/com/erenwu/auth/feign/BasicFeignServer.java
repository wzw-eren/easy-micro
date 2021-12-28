package com.erenwu.auth.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: wuzewei03
 * @create: 2021-11-16
 */
@FeignClient("basic")
public interface BasicFeignServer {

    @GetMapping("/basic/get")
    String getConfig();

}
