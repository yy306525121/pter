package cn.codeyang.pter.module.system.service.impl;

import cn.codeyang.pter.common.core.constant.CacheConstants;
import cn.codeyang.pter.module.system.entity.SysConfig;
import cn.codeyang.pter.module.system.repository.SysConfigRepository;
import cn.codeyang.pter.module.system.service.SysConfigService;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysConfigServiceImpl implements SysConfigService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SysConfigRepository configRepository;

    @Override
    public boolean selectCaptchaEnabled() {
        String captchaEnabled = selectConfigByKey("sys.account.captchaEnabled");
        if (StrUtil.isEmpty(captchaEnabled)) {
            return true;
        }
        return Convert.toBool(captchaEnabled);
    }

    @Override
    public String selectConfigByKey(String configKey) {
        String configValue = (String) redisTemplate.opsForValue().get(getCacheKey(configKey));
        if (StrUtil.isNotEmpty(configValue)) {
            return configValue;
        }
        Optional<SysConfig> optional = configRepository.findByConfigKey(configKey);
        if (optional.isPresent()) {
            SysConfig sysConfig = optional.get();
            redisTemplate.opsForValue().set(getCacheKey(configKey), sysConfig.getConfigValue());
            return sysConfig.getConfigValue();
        }
        return StrUtil.EMPTY;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private String getCacheKey(String configKey) {
        return CacheConstants.SYS_CONFIG_KEY + configKey;
    }
}
