package com.erenwu.auth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.erenwu.auth.mapper.MenuMapper;
import com.erenwu.auth.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单实现类
 *
 * @author: erenwu
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;


    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        if (CollectionUtils.isEmpty(perms)) {
            return permsSet;
        }
        permsSet.addAll(perms);
        return permsSet;
    }
}
