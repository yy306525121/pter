package cn.codeyang.pter.common.core.constant;

/**
 * @author yangzy
 */
public interface CacheConstants {
    /**
     * 登录账户密码错误次数 redis key
     */
    String PWD_ERR_COUNT_KEY = "pwd_err_count:";

    /**
     * 登录用户 redis key
     */
    String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 验证码 redis key
     */
    String CAPTCHA_CODE_KEY = "captcha_codes:";

    /**
     * 参数管理 cache key
     */
    String SYS_CONFIG_KEY = "sys_config:";

    /**
     * 防重提交 redis key
     */
    String REPEAT_SUBMIT_KEY = "repeat_submit:";
}
