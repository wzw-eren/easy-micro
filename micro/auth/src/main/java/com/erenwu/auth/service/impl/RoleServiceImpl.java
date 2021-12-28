package com.erenwu.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.erenwu.auth.entity.Role;
import com.erenwu.auth.mapper.RoleMapper;
import com.erenwu.auth.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色业务实现类
 *
 * @author: erenwu
 */
@Service
@Slf4j
public class RoleServiceImpl implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        List<Role> perms = roleMapper.selectRolePermissionByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        if (CollectionUtils.isEmpty(perms)) {
            return permsSet;
        }
        List<String> roleKeyList = perms.stream().map(Role::getRoleKey).collect(Collectors.toList());
        permsSet.addAll(roleKeyList);
        return permsSet;
    }
}
