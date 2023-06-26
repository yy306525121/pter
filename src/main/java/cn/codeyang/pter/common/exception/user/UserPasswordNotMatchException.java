package cn.codeyang.pter.common.exception.user;

/**
 * @author yangzy
 */
public class UserPasswordNotMatchException extends UserException {
    public UserPasswordNotMatchException() {
        super("user.password.not.match", null);
    }
}
