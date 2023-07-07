package cn.codeyang.pter.module.system.entity;

import cn.codeyang.pter.common.core.domain.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yangzy
 */
@Getter
@Setter
@Entity
@Table(name = "sys_config")
public class SysConfig extends BaseEntity {


    private static final long serialVersionUID = -4857183645887378642L;
    private String configName;
    private String configKey;
    private String configValue;
    private String configType;
    private String remark;
}
