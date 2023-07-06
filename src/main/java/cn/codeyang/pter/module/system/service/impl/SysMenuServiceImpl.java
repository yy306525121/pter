package cn.codeyang.pter.module.system.service.impl;

import cn.codeyang.pter.module.system.entity.SysMenu;
import cn.codeyang.pter.module.system.repository.SysMenuRepository;
import cn.codeyang.pter.module.system.service.SysMenuService;
import cn.codeyang.pter.module.system.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuRepository menuRepository;
    @Override
    public List<SysMenu> selectMenuTreeAll() {
        return menuRepository.findAll();
    }

    @Override
    public List<SysMenu> selectMenuTreeByUser(SysUser user) {
        List<SysMenu> menuList = new ArrayList<>();
        user.getRoles().forEach(role -> {
            menuList.addAll(role.getMenus());
        });
        return menuList;
    }
}
