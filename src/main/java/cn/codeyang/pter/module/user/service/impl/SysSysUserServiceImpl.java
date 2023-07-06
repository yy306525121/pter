package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.module.user.entity.SysUser;
import cn.codeyang.pter.module.user.repository.SysUserRepository;
import cn.codeyang.pter.module.user.service.SysUserService;
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
