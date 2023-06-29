package cn.codeyang.pter.module.menu.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

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

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 路由参数
     */
    private String query;

    /**
     * 是否为外链（0是 1否）
     */
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    private String isCache;

    /**
     * 类型（0目录 1菜单 2按钮）
     */
    private Integer menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    private Integer visible;

    /**
     * 菜单状态（0显示 1隐藏）
     */
    private String status;

    /**
     * 权限字符串
     */
    private String perms;

    /**
     * 菜单图标
     */
    private String icon;

    private String remark;
}
