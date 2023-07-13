package cn.codeyang.pter.module.menu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
public class SysMenuAddReqDto implements Serializable {

    @NotEmpty(message = "名称不能为空")
    private String name;
    @NotEmpty(message = "路径不能为空")
    private String path;
    @NotEmpty(message = "标题不能为空")
    private String title;
    private String icon;
    private String badge;
    /**
     * _self|_blank
     */
    private String target;
    private String link;
    @NotEmpty(message = "组件不能为空")
    private String component;
    private Boolean renderMenu;
    private String permission;
    private Long parentId = 0L;
    @NotNull(message = "排序不能为空")
    private Integer sort;
    private Boolean cacheable;
}
