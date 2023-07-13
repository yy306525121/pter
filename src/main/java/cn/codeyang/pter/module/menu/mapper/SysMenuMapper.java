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

    List<SysMenu> listMenusByRoleId(Long roleId);

    List<SysMenu> selectMenuTreeByUserId(Long userId);

    List<String> selectMenuPermsByUserId(Long userId);

    List<SysMenu> selectMenuTreeAll();
}
