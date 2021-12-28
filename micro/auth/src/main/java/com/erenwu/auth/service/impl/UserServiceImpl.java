package com.erenwu.auth.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.erenwu.auth.entity.User;
import com.erenwu.auth.mapper.UserMapper;
import com.erenwu.auth.service.PermissionService;
import com.erenwu.auth.service.UserService;
import com.erenwu.common.constants.RedisConstants;
import com.erenwu.common.dto.auth.UserDTO;
import com.erenwu.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 用户业务实现类
 *
 * @author: erenwu
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedisService redisService;

    @Resource
    private PermissionService permissionService;

    @Override
    public User getUserByLoginName(String loginName) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.and(i -> i.eq(User::getLoginName, loginName));
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User addUser(UserDTO dto) {
        return null;
    }

    @Override
    public UserDTO getUserById(Long userId) {
        String userString = redisService.getCacheObject(RedisConstants.USER_INFO_KEY + userId);
        if (StringUtils.isEmpty(userString)) {
            UserDTO dto = new UserDTO();
            User user = userMapper.selectById(userId);
            if (user == null) {
                return dto;
            }
            Set<String> rolePermission = permissionService.getRolePermission(userId);
            Set<String> menuPermission = permissionService.getMenuPermission(userId);
            BeanUtils.copyProperties(user, dto);
            dto.setRoles(rolePermission);
            dto.setPermissions(menuPermission);
            return dto;
        }
        return JSONObject.parseObject(userString, UserDTO.class);
    }
}
