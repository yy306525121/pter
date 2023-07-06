package cn.codeyang.pter.framework.security.service;

import cn.codeyang.pter.common.core.constant.CacheConstants;
import cn.codeyang.pter.common.core.constant.CommonConstants;
import cn.codeyang.pter.common.core.domain.model.LoginUser;
import cn.codeyang.pter.common.exception.BizException;
import cn.codeyang.pter.common.exception.user.CaptchaException;
import cn.codeyang.pter.common.exception.user.CaptchaExpireException;
import cn.codeyang.pter.common.exception.user.UserPasswordNotMatchException;
import cn.codeyang.pter.common.utils.ServletUtils;
import cn.codeyang.pter.common.utils.ip.IpUtils;
import cn.codeyang.pter.framework.security.context.AuthenticationContextHolder;
import cn.codeyang.pter.module.system.service.SysConfigService;
import cn.codeyang.pter.module.system.entity.SysUser;
import cn.codeyang.pter.module.system.service.SysUserService;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 登录校验方法
 *
 * @author ruoyi
 */
@Component
@RequiredArgsConstructor
public class LoginService {
    private final TokenService tokenService;

    private final AuthenticationManager authenticationManager;

    private final RedisTemplate<String, Object> redisTemplate;

    private final SysUserService userService;

    private final SysConfigService configService;

    // 是否允许账户多终端同时登录（true允许 false不允许）
    @Value("${token.soloLogin}")
    private boolean soloLogin;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                //TODO-yangzy 记录日志
                throw new UserPasswordNotMatchException();
            } else {
                // TODO-yangzy 记录日志
                throw new BizException(e.getMessage());
            }
        }
        // TODO-yangzy 记录日志
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());

        if (!soloLogin) {
            // 如果用户不允许多终端同时登录，清除缓存信息
            String userIdKey = CommonConstants.LOGIN_USERID_KEY + loginUser.getUser().getId();
            String userKey = (String) redisTemplate.opsForValue().get(userIdKey);
            if (StrUtil.isNotEmpty(userKey)) {
                redisTemplate.delete(userIdKey);
                redisTemplate.delete(userKey);
            }
        }

        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StrUtil.emptyIfNull(uuid);
        String captcha = (String) redisTemplate.opsForValue().get(verifyKey);
        redisTemplate.delete(verifyKey);
        if (captcha == null) {
            //TODO-yangzy 记录日志
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            // TODO-yangzy 记录日志
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser user = userService.findById(userId);
        user.setLoginIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        user.setLoginDate(LocalDate.now());
        userService.updateUserProfile(user);
    }
}
