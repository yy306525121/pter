package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.module.user.entity.SysUser;
import cn.codeyang.pter.module.user.mapper.SysUserMapper;
import cn.codeyang.pter.module.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysSysUserServiceImpl implements SysUserService {
    private final SysUserMapper userMapper;

    @Override
    public SysUser selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public void updateUserProfile(SysUser user) {
        userMapper.updateUserProfile(user);
    }
}
