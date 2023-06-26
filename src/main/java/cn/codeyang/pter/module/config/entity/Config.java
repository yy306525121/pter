package cn.codeyang.pter.module.config.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
@Mapper
public class Config implements Serializable {
    private Long id;
    private String configName;
    private String configKey;
    private String configValue;
    private String configType;
    private String remark;
}
