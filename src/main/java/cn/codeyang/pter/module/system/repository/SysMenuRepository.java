package cn.codeyang.pter.module.system.repository;

import cn.codeyang.pter.module.system.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangzy
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu, Long> {

}
