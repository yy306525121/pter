package cn.codeyang.pter.module.menu.mapper;

import cn.codeyang.pter.module.menu.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangzy
 */
@Mapper
public interface SysMenuMapper {

    @Select("SELECT sys_menu.id," +
            "               sys_menu.menu_name," +
            "               sys_menu.parent_id," +
            "               sys_menu.order_num," +
            "               sys_menu.path," +
            "               sys_menu.component," +
            "               sys_menu.query," +
            "               sys_menu.is_frame," +
            "               sys_menu.is_cache," +
            "               sys_menu.menu_type," +
            "               sys_menu.visible," +
            "               sys_menu.status," +
            "               sys_menu.perms," +
            "               sys_menu.icon," +
            "               sys_menu.remark" +
            "        FROM sys_menu" +
            "                 LEFT JOIN sys_role_menu ON sys_menu.id = sys_role_menu.menu_id" +
            "        WHERE sys_role_menu.role_id = #{roleId}" +
            "        ORDER BY sys_menu.order_num DESC")
    List<SysMenu> listMenusByRoleId(Long roleId);

}
