package com.erenwu.auth.service;

import java.util.Set;

/**
 * 权限服务类
 *
 * @author erenwu
 */
public interface PermissionService {

    /**
     * 获取角色数据权限
     * 
     * @param userId 用户Id
     * @return 角色权限信息
     */
    Set<String> getRolePermission(Long userId);

    /**
     * 获取菜单数据权限
     * 
     * @param userId 用户Id
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(Long userId);
}
