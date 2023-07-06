package cn.codeyang.pter.module.system.entity;


import cn.codeyang.pter.common.core.domain.model.BaseEntity;
import cn.codeyang.pter.module.system.entity.SysRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yangzy
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "sys_user")
@ToString
@DynamicUpdate
public class SysUser extends BaseEntity {

    private String nickName;
    private String username;
    @JsonIgnore
    private String password;
    private String loginIp;

    private LocalDate loginDate;

    private String avatar;

    @ManyToMany(targetEntity = SysRole.class, fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "sys_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<SysRole> roles = new HashSet<>();

}
