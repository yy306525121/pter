package cn.codeyang.pter.module.config.service.impl;

import cn.codeyang.pter.common.core.constant.CacheConstants;
import cn.codeyang.pter.module.config.entity.SysConfig;
import cn.codeyang.pter.module.config.mapper.SysConfigMapper;
import cn.codeyang.pter.module.config.service.SysConfigService;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author yangzy
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysConfigServiceImpl implements SysConfigService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final SysConfigMapper configMapper;

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
        SysConfig config = configMapper.selectConfig(configKey);
        if (config != null) {
            redisTemplate.opsForValue().set(getCacheKey(configKey), config.getConfigValue());
            return config.getConfigValue();
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
