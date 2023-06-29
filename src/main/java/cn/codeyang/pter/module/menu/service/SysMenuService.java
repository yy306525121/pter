package cn.codeyang.pter.module.menu.service;

import cn.codeyang.pter.module.menu.entity.SysMenu;

import java.util.List;

/**
 * @author yangzy
 */
public interface SysMenuService {
    /**
     * 通过角色编号查询URL 权限
     * @param roleId 角色ID
     * @return 菜单列表
     */
    List<SysMenu> findMenuByRoleId(Long roleId);

}
