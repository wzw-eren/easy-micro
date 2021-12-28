package com.erenwu.common;


import lombok.extern.slf4j.Slf4j;

/**
 * BootstrapProfile 设置
 *
 * @author eren
 */
@Slf4j
public class BootstrapProfile {

    /**
     * 设置启动的profile 文件
     */
    public static void setBootstrapProfile() {
        String profile = System.getProperty("spring.profiles.active");
        log.info("当前启动参数中选择的环境文件为:[{}]",profile==null?"默认开发":profile);
        if (profile != null) {
            System.setProperty("spring.cloud.bootstrap.name", "bootstrap-" + profile);
        }
    }
}
