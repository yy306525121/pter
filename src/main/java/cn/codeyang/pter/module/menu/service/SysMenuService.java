package cn.codeyang.pter.module.menu.service;

import cn.codeyang.pter.module.menu.entity.SysMenu;
import cn.codeyang.pter.module.user.entity.SysUser;

import java.util.List;

/**
 * @author yangzy
 */
public interface SysMenuService {
    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenuTreeByUser(SysUser user);
}
