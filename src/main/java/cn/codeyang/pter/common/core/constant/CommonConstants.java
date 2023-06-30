package cn.codeyang.pter.common.core.constant;

/**
 * @author yangzy
 */
public interface CommonConstants {
    /**
     * 成功标记
     */
    Integer SUCCESS = 0;

    /**
     * 失败标记
     */
    Integer FAIL = 1;

    /**
     * 下载器token
     */
    String DOWNLOADER_TOKEN_KEY = "downloader:";

    /**
     * 登录用户编号 redis key
     */
    String LOGIN_USERID_KEY = "login_userid:";

    /**
     * 令牌前缀
     */
    String LOGIN_USER_KEY = "login_user_key";

    /**
     * 令牌前缀
     */
    String TOKEN_PREFIX = "Bearer ";

    /**
     * 资源映射路径 前缀
     */
    String RESOURCE_PREFIX = "/profile";

    /**
     * 验证码有效期（分钟）
     */
    Integer CAPTCHA_EXPIRATION = 3;
    String HTTP = "http://";
    String HTTPS = "https://";
}
