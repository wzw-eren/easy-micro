package com.erenwu.auth.service;

import java.util.Set;

/**
 * 角色 服务类
 *
 * @author erenwu
 */
public interface RoleService {

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectRolePermissionByUserId(Long userId);
}
