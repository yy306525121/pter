package cn.codeyang.pter.module.menu.service.impl;

import cn.codeyang.pter.module.menu.entity.SysMenu;
import cn.codeyang.pter.module.menu.mapper.SysMenuMapper;
import cn.codeyang.pter.module.menu.service.SysMenuService;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuMapper menuMapper;

    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        return menuMapper.listMenusByRoleId(roleId);
    }

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StrUtil.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectMenuTreeAll() {
        return menuMapper.selectMenuTreeAll();
    }

    @Override
    public List<SysMenu> selectMenuTreeByUserId(Long id) {
        return menuMapper.selectMenuTreeByUserId(id);
    }
}
