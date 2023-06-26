package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.module.user.entity.User;
import cn.codeyang.pter.module.user.mapper.UserMapper;
import cn.codeyang.pter.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public User selectByUsername(String username) {

        return userMapper.selectByUsername(username);
    }

    @Override
    public void updateUserProfile(User user) {
        userMapper.updateUserProfile(user);
    }
}
