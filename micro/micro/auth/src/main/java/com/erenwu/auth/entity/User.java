package com.erenwu.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * 用户实体
 *
 * @author: erenwu
 * @create: 2021-12-14
 */
@Data
@Accessors(chain = true)
@TableName("t_user")
public class User {

    @ApiModelProperty(value = "主键")
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    @ApiModelProperty(value = "登录账号")
    @TableField("login_name")
    private String loginName;

    @ApiModelProperty(value = "用户昵称")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty(value = "帐号状态（0正常 1停用）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "删除标志（0代表存在 2代表删除）")
    @TableField("del_flag")
    private String delFlag;

    @ApiModelProperty(value = "创建者")
    @TableField("create_by")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "修改人")
    @TableField("update_by")
    private String updateBy;
}
