package cn.codeyang.pter.module.user.mapper;

import cn.codeyang.pter.module.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user")
    List<User> list();

    @Select("SELECT * FROM t_user where username = #{username}")
    User selectByUsername(String username);

    @Update("update t_user set login_ip = #{user.loginIp}, login_date = #{user.loginDate} where id = #{user.id}}")
    void updateUserProfile(User user);
}
