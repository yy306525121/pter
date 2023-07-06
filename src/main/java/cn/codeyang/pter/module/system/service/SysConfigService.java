package cn.codeyang.pter.module.system.service;

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
