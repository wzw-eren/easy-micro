package com.erenwu.auth.service;

import java.util.Set;

/**
 * 菜单 服务类
 *
 * @author erenwu
 */
public interface MenuService {

    /**
     * 根据用户ID查询权限
     *
     * @param userId
     * @return
     */
    Set<String> selectMenuPermsByUserId(Long userId);
}
