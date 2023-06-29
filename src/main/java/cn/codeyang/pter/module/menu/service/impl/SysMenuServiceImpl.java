package cn.codeyang.pter.module.menu.service.impl;

import cn.codeyang.pter.module.menu.entity.SysMenu;
import cn.codeyang.pter.module.menu.mapper.SysMenuMapper;
import cn.codeyang.pter.module.menu.service.SysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl implements SysMenuService {
    private final SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findMenuByRoleId(Long roleId) {
        return sysMenuMapper.listMenusByRoleId(roleId);
    }
}
