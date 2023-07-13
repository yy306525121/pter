package cn.codeyang.pter.module.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 路由配置信息
 *
 * @author ruoyi
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RouterRspDto implements Serializable {
    private static final long serialVersionUID = -1521349725569406997L;
    private Long id;
    private String name;
    private String path;
    private String title;
    private String icon;
    private String badge;
    /**
     * _self|_blank
     */
    private String target;
    private String link;
    private String component;
    private Boolean renderMenu;
    private String permission;
    private Long parentId;

    private List<RouterRspDto> children;
}
