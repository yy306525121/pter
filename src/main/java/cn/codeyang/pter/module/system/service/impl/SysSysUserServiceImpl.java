package cn.codeyang.pter.module.system.service.impl;

import cn.codeyang.pter.module.system.entity.SysUser;
import cn.codeyang.pter.module.system.repository.SysUserRepository;
import cn.codeyang.pter.module.system.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class SysSysUserServiceImpl implements SysUserService {
    private final SysUserRepository userRepository;

    @Override
    public SysUser selectByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public void updateUserProfile(SysUser user) {
        userRepository.save(user);
    }

    @Override
    public SysUser findById(Long userId) {
        Optional<SysUser> user = userRepository.findById(userId);
        return user.orElse(null);
    }
}
