package com.erenwu.common.handler;

import com.erenwu.common.dto.auth.LoginUserDTO;
import com.erenwu.common.utils.JwtUtil;
import com.erenwu.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * http请求处理
 *
 * @author erenwu
 */
@Slf4j
public class HttpRequestHandler {

    /**
     * 获取当前登陆用户的信息
     * @return
     */
    public static LoginUserDTO getRequestUser() {
        ServletRequestAttributes servletRequestAttributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        Assert.notNull(servletRequestAttributes, "servletRequestAttributes为空！");
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String token = request.getHeader("X-Request-Token");
        if (StringUtils.isEmpty(token)) {
            return null;
        }
        return JwtUtil.getUser(token);
    }

}
