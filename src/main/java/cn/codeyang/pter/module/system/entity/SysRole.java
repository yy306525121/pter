package cn.codeyang.pter.module.system.entity;

import cn.codeyang.pter.common.core.domain.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author yangzy
 */
@Getter
@Setter
@Entity
@Table(name = "sys_role")
public class SysRole extends BaseEntity {


    private static final long serialVersionUID = -5119397650313795980L;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String dataScope;
    /**
     * 角色状态（0正常 1停用）
     */
    private String status;
    private String remark;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "sys_role_menu",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id", referencedColumnName = "id")
    )
    private Set<SysMenu> menus = new LinkedHashSet<>();

    @ManyToMany(targetEntity = SysUser.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<SysUser> users = new LinkedHashSet<>();

}
