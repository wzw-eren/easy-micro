package com.erenwu.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 角色和菜单关联实体
 *
 * @author: erenwu
 */
@Data
@Accessors(chain = true)
@TableName("t_role_menu")
public class RoleMenu {

    @ApiModelProperty(value = "角色id")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "菜单ID")
    @TableField(value = "menu_id")
    private Long menuId;
}
