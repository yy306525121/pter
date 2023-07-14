package cn.codeyang.pter.module.site.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
public class SiteAddReqDto implements Serializable {

    private static final long serialVersionUID = -4510501213400529370L;

    @NotEmpty(message = "名称不能为空")
    private String name;
    @NotEmpty(message = "cookie不能为空")
    private String cookie;
    private String cron;
    private Boolean enable;
    /**
     * 优先级(数字越小优先级越高)
     */
    private Integer priority;
}
