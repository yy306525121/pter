package cn.codeyang.pter.module.config.mapper;

import cn.codeyang.pter.module.config.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangzy
 */
@Mapper
public interface ConfigMapper {
    @Select("select * from t_config where config_key = #{configKey}")
    Config selectConfig(String configKey);
}
