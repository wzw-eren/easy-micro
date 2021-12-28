package com.erenwu.common.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

/**
 * 登录用户 dto
 *
 * @author eren
 */
@Data
@ApiModel
public class LoginUserDTO {

    @ApiModelProperty(value = "主键")
    private Long userId;

    @ApiModelProperty(value = "登录账号")
    private String loginName;

    @ApiModelProperty(value = "用户昵称")
    private String userName;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty(value = "jwt令牌")
    private String jwt;

    /**
     * 权限列表
     */
    @ApiModelProperty(value = "权限列表")
    private Set<String> permissions;

    /**
     * 角色列表
     */
    @ApiModelProperty(value = "角色列表")
    private Set<String> roles;
}
