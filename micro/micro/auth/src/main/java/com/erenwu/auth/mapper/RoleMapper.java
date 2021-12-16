package com.erenwu.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.erenwu.auth.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色 MAPPER
 *
 * @author erenwu
 */
@Repository
public interface RoleMapper extends BaseMapper<Role>  {

    /**
     * 根据用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色列表
     */
     List<Role> selectRolePermissionByUserId(Long userId);
}
