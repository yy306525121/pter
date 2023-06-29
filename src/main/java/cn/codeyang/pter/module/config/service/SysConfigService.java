package cn.codeyang.pter.module.config.service;

/**
 * @author yangzy
 */
public interface SysConfigService {
    boolean selectCaptchaEnabled();

    /**
     * 根据键名查询参数配置信息
     * @param configKey 参数键名
     * @return 参数键值
     */
    String selectConfigByKey(String configKey);
}
