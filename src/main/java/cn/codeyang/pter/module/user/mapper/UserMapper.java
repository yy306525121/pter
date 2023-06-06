package cn.codeyang.pter.module.user.mapper;

import cn.codeyang.pter.module.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user")
    List<User> list();
}
