package cn.codeyang.pter.module.menu.service;

import cn.codeyang.pter.module.menu.entity.SysMenu;

import java.util.List;
import java.util.Set;

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

    /**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
    public Set<String> selectMenuPermsByUserId(Long userId);

    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenuTreeByUserId(Long id);
}
