package cn.codeyang.pter.module.pter.repository;

import cn.codeyang.pter.module.pter.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yangzy
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
