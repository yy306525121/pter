package cn.codeyang.pter.module.system.entity;

import cn.codeyang.pter.common.core.domain.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author yangzy
 */
@Data
@Entity
@Table(name = "sys_config")
public class SysConfig extends BaseEntity {
    private String configName;
    private String configKey;
    private String configValue;
    private String configType;
    private String remark;
}
