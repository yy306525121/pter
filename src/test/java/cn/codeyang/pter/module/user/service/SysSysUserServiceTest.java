package cn.codeyang.pter.module.user.service;

import cn.codeyang.pter.module.system.entity.SysRole;
import cn.codeyang.pter.module.system.entity.SysUser;
import cn.codeyang.pter.module.system.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author yangzy
 */
@SpringBootTest
class SysSysUserServiceTest {
    @Autowired
    private SysUserService userService;

    @Test
    @Transactional
    void selectByUsername() {
        SysUser sysUser = userService.selectByUsername("admin");
        Set<SysRole> roles = sysUser.getRoles();
        System.out.println(sysUser);
    }
}