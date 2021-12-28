package com.erenwu.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户 角色关联实体
 *
 * @author: erenwu
 */
@Data
@Accessors(chain = true)
@TableName("t_user_role")
public class UserRole {

    @ApiModelProperty(value = "角色id")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "用户id")
    @TableField(value = "user_id")
    private Long userId;
}
