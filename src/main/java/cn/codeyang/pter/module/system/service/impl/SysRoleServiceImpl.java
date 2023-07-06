package cn.codeyang.pter.module.system.service.impl;

import cn.codeyang.pter.module.system.repository.SysRoleRepository;
import cn.codeyang.pter.module.system.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl implements SysRoleService {
    private final SysRoleRepository roleRepository;
}
