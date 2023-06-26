package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.common.core.domain.model.LoginUser;
import cn.codeyang.pter.common.exception.BizException;
import cn.codeyang.pter.common.utils.MessageUtils;
import cn.codeyang.pter.module.user.entity.User;
import cn.codeyang.pter.module.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;
    private final PasswordService passwordService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectByUsername(username);
        if (user == null) {
            log.info("登录用户：{} 不存在.", username);
            throw new BizException(MessageUtils.message("user.not.exists"));
        }
        passwordService.validate(user);
        return createLoginUser(user);
    }


    public UserDetails createLoginUser(User user) {
        // TODO-yangzy 这里需要继续补充
        return new LoginUser(user.getId(), null, null, user, new HashSet<>());
    }

}
