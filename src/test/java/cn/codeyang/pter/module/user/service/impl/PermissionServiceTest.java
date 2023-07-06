package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.framework.security.service.PermissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;


/**
 * @author yangzy
 */
@SpringBootTest
class PermissionServiceTest {
    @Autowired
    private PermissionService permissionService;

    @Test
    @Transactional
    void getRolePermission() {
        Set<String> rolePermission = permissionService.getRolePermission(1L);
    }
}