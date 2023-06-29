package cn.codeyang.pter.module.role.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

/**
 * @author yangzy
 */
@Data
@Mapper
public class SysRole implements Serializable {


    private static final long serialVersionUID = 4142677711076392616L;

    private Long id;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    private String remark;
}
