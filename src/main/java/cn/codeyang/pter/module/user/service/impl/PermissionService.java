package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.module.menu.entity.SysMenu;
import cn.codeyang.pter.module.menu.service.SysMenuService;
import cn.codeyang.pter.module.role.entity.SysRole;
import cn.codeyang.pter.module.role.service.SysRoleService;
import cn.codeyang.pter.module.user.entity.SysUser;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yangzy
 */
@Component
@RequiredArgsConstructor
public class PermissionService {
    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;

    public Set<String> getRolePermission(SysUser user) {
        List<SysRole> sysRoleList = sysRoleService.findRolesByUserId(user.getId());
        return sysRoleList.stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
    }

    public Set<SysMenu> getMenuPermission(SysUser user) {
        Set<SysMenu> menus = new HashSet<>();

        List<SysRole> roleIds = sysRoleService.findRolesByUserId(user.getId());
        roleIds.forEach(role -> {
            List<SysMenu> menuList = sysMenuService.findMenuByRoleId(role.getId()).stream().toList();
            menus.addAll(menuList);
        });

        return menus;
    }
}
