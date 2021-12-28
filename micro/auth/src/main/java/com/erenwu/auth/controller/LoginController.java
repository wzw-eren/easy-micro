package com.erenwu.auth.controller;

import com.erenwu.auth.feign.BasicFeignServer;
import com.erenwu.auth.service.LoginService;
import com.erenwu.common.auth.annotation.RequiresLogin;
import com.erenwu.common.auth.annotation.RequiresPermissions;
import com.erenwu.common.dto.auth.LoginUserDTO;
import com.erenwu.common.dto.auth.UserDTO;
import com.erenwu.common.utils.JwtUtil;
import com.erenwu.common.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: erenwu
 */
@RestController
@RequestMapping("/auth/login")
public class LoginController {

    @Resource
    private BasicFeignServer basicFeignServer;

    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public R<LoginUserDTO> login(@RequestBody UserDTO dto) {
        return R.buildSuccess(loginService.login(dto));
    }

    @GetMapping("/parse")
    @RequiresLogin
    public Boolean parse(String jwt) {
        return JwtUtil.checkSign(jwt);
    }

    @GetMapping("/feign")
    @RequiresPermissions("111")
    public String getFeign() {
        String config = basicFeignServer.getConfig();
        return "auth服务获取basic配置-" + config;
    }

}
