package com.erenwu.auth.service;

import com.erenwu.auth.entity.User;
import com.erenwu.common.dto.auth.UserDTO;

/**
 * 用户服务
 *
 * @author erenwu
 */
public interface UserService {

    /**
     * 根据登录账号获取用户信息
     *
     * @param loginName
     * @return
     */
    User getUserByLoginName(String loginName);

    /**
     * 添加用户
     *
     * @param dto
     * @return
     */
    User addUser(UserDTO dto);

    /**
     * 根据用户id获取用户完整信息
     *
     * @param userId
     * @return
     */
    UserDTO getUserById(Long userId);
}
