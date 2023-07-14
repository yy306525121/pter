package cn.codeyang.pter.module.site.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
public class Site implements Serializable {
    private Long id;
    private String name;
    private String cookie;
    private String cron;
    private Boolean enable;
    /**
     * 优先级(数字越小优先级越高)
     */
    private Integer priority;
}
