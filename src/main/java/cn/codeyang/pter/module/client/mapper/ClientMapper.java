package cn.codeyang.pter.module.client.mapper;


import cn.codeyang.pter.module.client.entity.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 下载器表(Client)表数据库访问层
 *
 * @author yangzy
 * @since 2023-06-07 15:01:52
 */
@Mapper
public interface ClientMapper {

    @Select("SELECT * FROM t_client")
    List<Client> selectAll();
}

