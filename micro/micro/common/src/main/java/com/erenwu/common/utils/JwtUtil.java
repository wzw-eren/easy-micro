package com.erenwu.common.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.erenwu.common.exception.SystemRuntimeException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JwtUtil 工具类
 *
 * @author: eren
 */
@Slf4j
public class JwtUtil {

    /**
     * jwt 密钥
     */
    private static final String SECRET = "jwt_secret";

    /**
     * 生成签名
     *
     * @param userId
     * @return
     */
    public static String sign(Long userId, String loginName, String userName, Long expireTime) {
        try {
            Date date = new Date(System.currentTimeMillis() + expireTime);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    // 将 user id 保存到 token 里面
                    .withClaim("userId", userId)
                    .withClaim("userName", userName)
                    .withClaim("loginName", loginName)
                    // 五分钟后token过期
                    .withExpiresAt(date)
                    // token 的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 根据token获取userId
     *
     * @param token
     * @return
     */
    public static Long getUserId(String token) {
        try {
            return JWT.decode(token).getClaim("userId").asLong();
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 校验token
     * @param token
     * @return
     */
    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    // .withClaim("username", username)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            throw new SystemRuntimeException("token 无效，请重新获取");
        }
    }
}