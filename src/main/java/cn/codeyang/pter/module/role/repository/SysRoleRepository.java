package cn.codeyang.pter.module.role.repository;

import cn.codeyang.pter.module.role.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangzy
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
