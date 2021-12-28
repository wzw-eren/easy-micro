package com.erenwu.redis;

import lombok.Builder;
import lombok.Data;

/**
 * RedisKey
 *
 * @author LiKe
 * @version 1.0.0
 * @date 2020-05-12 10:33
 */
@Data
@Builder
public final class RedisKey {

    public static final String SEPARATOR = ".";

    /**
     * Redis key 的前缀
     */
    private String prefix;

    /**
     * Redis key 的内容
     */
    private String suffix;

    public String of() {
        return String.format("%s.%s", prefix, suffix);
    }
}
