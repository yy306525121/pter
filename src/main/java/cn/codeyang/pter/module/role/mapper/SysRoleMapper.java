package cn.codeyang.pter.module.role.mapper;

import cn.codeyang.pter.module.role.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yangzy
 */
@Mapper
public interface SysRoleMapper {

    @Select("SELECT sys_role.id, role_name, role_key, role_sort, data_scope, status, remark" +
            "        FROM sys_role," +
            "             sys_user_role" +
            "        WHERE sys_role.id = sys_user_role.role_id" +
            "          and sys_user_role.user_id = #{userId}")
    List<SysRole> listRolesByUserId(Long userId);
}
