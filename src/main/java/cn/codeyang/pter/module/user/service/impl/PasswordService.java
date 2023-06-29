package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.common.core.constant.CacheConstants;
import cn.codeyang.pter.common.exception.user.UserPasswordNotMatchException;
import cn.codeyang.pter.common.exception.user.UserPasswordRetryLimitExceedException;
import cn.codeyang.pter.common.utils.SecurityUtils;
import cn.codeyang.pter.framework.security.context.AuthenticationContextHolder;
import cn.codeyang.pter.module.user.entity.SysUser;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 登录密码方法
 *
 * @author ruoyi
 */
@Component
@RequiredArgsConstructor
public class PasswordService {
    private final RedisTemplate<String, Object> redisTemplate;

    @Value(value = "${user.password.maxRetryCount}")
    private int maxRetryCount;

    @Value(value = "${user.password.lockTime}")
    private int lockTime;

    /**
     * 登录账户密码错误次数缓存键名
     *
     * @param username 用户名
     * @return 缓存键key
     */
    private String getCacheKey(String username) {
        return CacheConstants.PWD_ERR_COUNT_KEY + username;
    }

    public void validate(SysUser user) {
        Authentication usernamePasswordAuthenticationToken = AuthenticationContextHolder.getContext();
        String username = usernamePasswordAuthenticationToken.getName();
        String password = usernamePasswordAuthenticationToken.getCredentials().toString();

        Integer retryCount = (Integer) redisTemplate.opsForValue().get(getCacheKey(username));

        if (retryCount == null) {
            retryCount = 0;
        }

        if (retryCount >= maxRetryCount) {
            // TODO-yangzy 异步记录日志
            throw new UserPasswordRetryLimitExceedException(maxRetryCount, lockTime);
        }

        if (!matches(user, password)) {
            retryCount = retryCount + 1;
            // TODO-yangzy 异步记录日志
            redisTemplate.opsForValue().set(getCacheKey(username), retryCount, lockTime, TimeUnit.MINUTES);
            throw new UserPasswordNotMatchException();
        } else {
            clearLoginRecordCache(username);
        }
    }

    public boolean matches(SysUser user, String rawPassword) {
        return SecurityUtils.matchesPassword(rawPassword, user.getPassword());
    }

    public void clearLoginRecordCache(String loginName) {
        if (Boolean.TRUE.equals(redisTemplate.hasKey(getCacheKey(loginName)))) {
            redisTemplate.delete(getCacheKey(loginName));
        }
    }
}
