package cn.codeyang.pter.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 获取i18n资源文件
 * @author yangzy
 */
public class MessageUtils {

    /**
     * 根据消息键和参数获取消息并委托给spring messageSource
     * @param code 消息键
     * @param args 参数
     * @return 获取到的国际化翻译值
     */
    public static String message(String code, Object... args) {
        MessageSource messageSource = SpringUtil.getBean(MessageSource.class);
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
