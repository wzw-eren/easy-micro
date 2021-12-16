package com.erenwu.auth.service;

import com.erenwu.common.dto.auth.LoginUserDTO;
import com.erenwu.common.dto.auth.UserDTO;

/**
 * 登录服务
 *
 * @author erenwu
 */
public interface LoginService {

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    LoginUserDTO login(UserDTO dto);
}
