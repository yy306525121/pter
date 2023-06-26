package cn.codeyang.pter.common.exception;

import lombok.Data;

/**
 * 业务异常
 * @author yangzy
 */
@Data
public class BizException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String message;

    public BizException() {
    }

    public BizException(String message) {
        this.message = message;
    }

    public BizException(String message, Integer code) {
        this.message = message;
        this.code = code;
    }


}
