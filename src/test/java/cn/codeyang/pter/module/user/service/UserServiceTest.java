package cn.codeyang.pter.module.user.service;

import cn.codeyang.pter.module.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yangzy
 */
@SpringBootTest
class UserServiceTest {
    @Autowired
    private UserService userService;

    @Test
    void selectByUsername() {
        User user = userService.selectByUsername("admin");
        System.out.println(user);
    }
}