package cn.codeyang.pter.common.exception;

import cn.codeyang.pter.common.utils.MessageUtils;
import cn.hutool.core.util.StrUtil;
import lombok.Getter;

/**
 * @author yangzy
 */
@Getter
public class BaseException extends RuntimeException {

    /**
     * 所属模块
     */
    private String module;

    /**
     * 错误码
     */
    private String code;

    /**
     * 错误码对应的参数
     */
    private Object[] args;

    /**
     * 错误消息
     */
    private String message;

    public BaseException(String module, String code, Object[] args, String message) {
        this.module = module;
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public BaseException(String code, Object[] args, String message) {
        this.code = code;
        this.args = args;
        this.message = message;
    }

    public BaseException(Object[] args, String message) {
        this.args = args;
        this.message = message;
    }

    public BaseException(String message) {
        this.message = message;
    }


    @Override
    public String getMessage() {
        String message = null;
        if (StrUtil.isNotEmpty(code)) {
            message = MessageUtils.message(code, args);
        }
        if (message == null) {
            message = this.message;
        }
        return message;
    }
}
