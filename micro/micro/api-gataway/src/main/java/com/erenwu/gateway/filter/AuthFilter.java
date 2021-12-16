package com.erenwu.gateway.filter;

import com.erenwu.common.constants.RedisConstants;
import com.erenwu.common.constants.TokenConstants;
import com.erenwu.common.exception.SystemRuntimeException;
import com.erenwu.common.redis.service.RedisService;
import com.erenwu.common.utils.JwtUtil;
import com.erenwu.common.utils.StringUtils;
import com.erenwu.gateway.config.ErenGatewayProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 认证服务透传处理
 *
 * @author erenwu
 */
@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Resource
    private ErenGatewayProperties erenGatewayProperties;

    @Resource
    private RedisService redisService;


    /**
     * Process the Web request and (optionally) delegate to the next {@code WebFilter}
     * through the given {@link GatewayFilterChain}.
     *
     * @param exchange the current server exchange
     * @param chain    provides a way to delegate to the next filter
     * @return {@code Mono<Void>} to indicate when request processing is complete
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        String url = request.getURI().getPath();
        // 跳过不需要验证的路径
        if (StringUtils.matches(url, Arrays.asList(erenGatewayProperties.getNoAuthUris()))) {
            return chain.filter(exchange);
        }
        String token = getToken(request);
        if (StringUtils.isEmpty(token)) {
            throw new SystemRuntimeException("token不能为null");
        }
        JwtUtil.checkSign(token);
        Long userId = JwtUtil.getUserId(token);
        String isLogin = redisService.getCacheObject(RedisConstants.USER_INFO_KEY + userId);
        if (StringUtils.isEmpty(isLogin)) {
            throw new SystemRuntimeException("登录状态已过期");
        }
        request = request.mutate()
                .header(erenGatewayProperties.getUserTokenHeader(), token).build();
        return chain.filter(exchange.mutate().request(request).build());
    }


    /**
     * 获取请求token
     */
    private String getToken(ServerHttpRequest request) {
        String token = request.getHeaders().getFirst(TokenConstants.AUTHENTICATION);
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StringUtils.isNotEmpty(token) && token.startsWith(TokenConstants.PREFIX)) {
            token = token.replaceFirst(TokenConstants.PREFIX, StringUtils.EMPTY);
        }
        return token;
    }

    /**
     * Get the order value of this object.
     * <p>Higher values are interpreted as lower priority. As a consequence,
     * the object with the lowest value has the highest priority (somewhat
     * analogous to Servlet {@code load-on-startup} values).
     * <p>Same order values will result in arbitrary sort positions for the
     * affected objects.
     *
     * @return the order value
     * @see #HIGHEST_PRECEDENCE
     * @see #LOWEST_PRECEDENCE
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
