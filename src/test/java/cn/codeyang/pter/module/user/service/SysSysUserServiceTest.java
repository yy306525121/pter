package cn.codeyang.pter.module.user.service;

import cn.codeyang.pter.module.user.entity.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author yangzy
 */
@SpringBootTest
class SysSysUserServiceTest {
    @Autowired
    private SysUserService userService;

    @Test
    void selectByUsername() {
        SysUser sysUser = userService.selectByUsername("admin");
        System.out.println(sysUser);
    }
}