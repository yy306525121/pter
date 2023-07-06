package cn.codeyang.pter.module.system.service;

import cn.codeyang.pter.module.system.entity.SysMenu;
import cn.codeyang.pter.module.system.entity.SysUser;

import java.util.List;

/**
 * @author yangzy
 */
public interface SysMenuService {
    List<SysMenu> selectMenuTreeAll();

    List<SysMenu> selectMenuTreeByUser(SysUser user);
}
