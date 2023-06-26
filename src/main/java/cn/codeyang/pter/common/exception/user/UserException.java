package cn.codeyang.pter.common.exception.user;

import cn.codeyang.pter.common.exception.BaseException;

/**
 * 用户信息异常类
 * @author yangzy
 */
public class UserException extends BaseException {
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}
