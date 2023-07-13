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

    @Select("select * from sys_menu menu left join sys_role_menu role_menu on menu.id = role_menu.menu_id where role_menu.role_id = #{roleId} and menu.status = '0' order by menu.sort")
    List<SysMenu> listMenusByRoleId(Long roleId);

    @Select("select distinct menu.* from sys_menu menu left join sys_role_menu role_menu on role_menu.menu_id = menu.id left join sys_user_role user_role on user_role.role_id = role_menu.role_id left join sys_role role on role.id = user_role.role_id left join sys_user user on user.id = user_role.user_id where user.id = #{userId} and menu.status = '0' and role.status = '0' order by menu.sort")
    List<SysMenu> selectMenuTreeByUserId(Long userId);

    @Select("select distinct menu.permission from sys_menu menu left join sys_role_menu role_menu on role_menu.menu_id = menu.id left join sys_user_role user_role on user_role.role_id = role_menu.menu_id left join sys_role role on role.id = user_role.role_id where menu.status = '0' and role.status = '0' and user_role.user_id = #{userId}")
    List<String> selectMenuPermsByUserId(Long userId);

    @Select("select * from sys_menu menu order by menu.sort")
    List<SysMenu> selectMenuTreeAll();
}
