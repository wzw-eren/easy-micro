package com.erenwu.gateway;

import com.erenwu.common.BootstrapProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description:
 * @author: erenwu
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        BootstrapProfile.setBootstrapProfile();
        SpringApplication.run(GatewayApplication.class, args);
    }
}
