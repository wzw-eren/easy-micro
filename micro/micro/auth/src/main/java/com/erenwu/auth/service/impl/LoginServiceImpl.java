package com.erenwu.auth.service.impl;

import cn.hutool.json.JSONUtil;
import com.erenwu.auth.entity.User;
import com.erenwu.auth.service.LoginService;
import com.erenwu.auth.service.PermissionService;
import com.erenwu.auth.service.UserService;
import com.erenwu.common.constants.RedisConstants;
import com.erenwu.common.dto.auth.LoginUserDTO;
import com.erenwu.common.dto.auth.UserDTO;
import com.erenwu.common.redis.service.RedisService;
import com.erenwu.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 登录实现类
 *
 * @author: erenwu
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private RedisService redisService;

    @Override
    public LoginUserDTO login(UserDTO dto) {
        // 暂时没有校验密码逻辑
        // 暂时没有多渠道登录方式，后续添加第三方登录等方式
        User user = userService.getUserByLoginName(dto.getLoginName());
        if (user == null) {
            user = userService.addUser(dto);
        }
        LoginUserDTO result = new LoginUserDTO();
        BeanUtils.copyProperties(user, result);
        // 获取用户角色和菜单权限
        Set<String> menuPermission = permissionService.getMenuPermission(user.getUserId());
        Set<String> rolePermission = permissionService.getRolePermission(user.getUserId());
        result.setRoles(rolePermission);
        result.setPermissions(menuPermission);
        generateJWT(result);
        saveUserInfoToRedis(result);
        return result;
    }

    /**
     * 生成jwt
     *
     * @param result
     */
    private void generateJWT(LoginUserDTO result) {
        String jwt = JwtUtil.sign(result.getUserId(), result.getUserName(), result.getLoginName(), getExpireTime());
        result.setJwt(jwt);
    }

    /**
     * 保存用户信息到redis
     *
     * @param result
     */
    private void saveUserInfoToRedis(LoginUserDTO result) {
        String userString = JSONUtil.toJsonStr(result);
        redisService.setCacheObject(RedisConstants.USER_INFO_KEY + result.getUserId(), userString, getExpireTime(), TimeUnit.MILLISECONDS);
        redisService.setCacheObject(RedisConstants.USER_LOGINNAME_ID_KEY + result.getLoginName(), result.getUserId().toString());
    }

    /**
     * 获取当前时间到24点的秒数
     *
     * @return
     */
    private Long getExpireTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - System.currentTimeMillis());
    }
}
