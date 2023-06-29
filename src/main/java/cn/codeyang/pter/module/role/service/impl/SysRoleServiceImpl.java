package cn.codeyang.pter.module.role.service.impl;

import cn.codeyang.pter.module.role.entity.SysRole;
import cn.codeyang.pter.module.role.mapper.SysRoleMapper;
import cn.codeyang.pter.module.role.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleMapper roleMapper;


    @Override
    public List<SysRole> findRolesByUserId(Long userId) {
        return roleMapper.listRolesByUserId(userId);
    }
}
