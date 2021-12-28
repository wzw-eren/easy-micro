package com.erenwu.auth.service.impl;

import com.erenwu.auth.service.MenuService;
import com.erenwu.auth.service.PermissionService;
import com.erenwu.auth.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * 权限业务实现类
 *
 * @author: erenwu
 */
@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private RoleService roleService;

    @Resource
    private MenuService menuService;

    @Override
    public Set<String> getRolePermission(Long userId) {
        return roleService.selectRolePermissionByUserId(userId);
    }

    @Override
    public Set<String> getMenuPermission(Long userId) {
        return menuService.selectMenuPermsByUserId(userId);
    }
}
