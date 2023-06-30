package cn.codeyang.pter.module.menu.mapper;

import cn.codeyang.pter.module.menu.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

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

    @Select("select distinct m.id," +
            "                m.parent_id," +
            "                m.menu_name," +
            "                m.path," +
            "                m.component," +
            "                m.query," +
            "                m.visible," +
            "                m.status," +
            "                ifnull(m.perms, '') as perms," +
            "                m.is_frame," +
            "                m.is_cache," +
            "                m.menu_type," +
            "                m.icon," +
            "                m.order_num " +
            "from sys_menu m" +
            "         left join sys_role_menu rm on m.id = rm.menu_id" +
            "         left join sys_user_role ur on rm.role_id = ur.role_id" +
            "         left join sys_role ro on ur.role_id = ro.id" +
            "         left join sys_user u on ur.user_id = u.id" +
            " where u.id = '1'" +
            "  and m.menu_type in ('M', 'C')" +
            "  and m.status = 0" +
            "  AND ro.status = 0" +
            " order by m.parent_id, m.order_num")
    List<SysMenu> selectMenuTreeByUserId(Long id);

    @Select("select distinct m.perms" +
            " from sys_menu m " +
            "         left join sys_role_menu rm on m.id = rm.menu_id " +
            "         left join sys_user_role ur on rm.role_id = ur.role_id " +
            "         left join sys_role r on r.id = ur.role_id " +
            "where m.status = '0' " +
            "  and r.status = '0' " +
            "  and ur.user_id = #{userId}")
    List<String> selectMenuPermsByUserId(Long userId);

    @Select("select distinct m.id, " +
            "                m.parent_id, " +
            "                m.menu_name, " +
            "                m.path, " +
            "                m.component, " +
            "                m.query, " +
            "                m.visible, " +
            "                m.status, " +
            "                ifnull(m.perms, '') as perms, " +
            "                m.is_frame, " +
            "                m.is_cache, " +
            "                m.menu_type, " +
            "                m.icon, " +
            "                m.order_num " +
            "from sys_menu m " +
            "where m.menu_type in ('M', 'C') " +
            "  and m.status = 0 " +
            "order by m.parent_id, m.order_num")
    List<SysMenu> selectMenuTreeAll();
}
