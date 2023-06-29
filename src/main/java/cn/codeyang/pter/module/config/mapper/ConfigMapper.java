package cn.codeyang.pter.module.config.mapper;

import cn.codeyang.pter.module.config.entity.SysConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangzy
 */
@Mapper
public interface ConfigMapper {
    @Select("select * from sys_config where config_key = #{configKey}")
    SysConfig selectConfig(String configKey);
}
