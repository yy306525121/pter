package cn.codeyang.pter.module.menu.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;
import java.util.List;

/**
 * @author yangzy
 */
@Data
@Mapper
public class SysMenu implements Serializable {

    /**
     * 菜单ID
     */
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
    private Integer sort;

    private List<SysMenu> children;
}
