package cn.codeyang.pter.module.user.mapper;

import cn.codeyang.pter.module.user.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM sys_user")
    List<SysUser> list();

    @Select("SELECT * FROM sys_user where username = #{username}")
    SysUser selectByUsername(String username);

    @Update("update sys_user set login_ip = #{loginIp}, login_date = #{loginDate} where id = #{id}")
    void updateUserProfile(SysUser user);
}
