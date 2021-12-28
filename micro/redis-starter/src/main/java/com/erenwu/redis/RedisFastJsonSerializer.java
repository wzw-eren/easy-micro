package com.erenwu.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * RedisFastJsonSerializer 是自定义的序列化和反序列化工具,
 * 为了往 Redis 中设值的时候, 自动将对象序列化, 取值的时候自动反序列化
 *
 * @author LiKe
 * @version 1.0.0
 * @date 2020-05-10 18:58
 */
public class RedisFastJsonSerializer<T> implements RedisSerializer<T> {

    private final Class<T> clazz;

    public RedisFastJsonSerializer(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (Objects.isNull(t)) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (Objects.isNull(bytes) || ArrayUtils.isEmpty(bytes)) {
            return null;
        }
        return JSON.parseObject(new String(bytes, StandardCharsets.UTF_8), clazz);
    }

}
