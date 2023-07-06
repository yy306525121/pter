package cn.codeyang.pter.web.controller;

import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.module.system.entity.SysUser;
import cn.codeyang.pter.module.system.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yangzy
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private SysUserRepository userRepository;

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/users")
    public R<List<SysUser>> listUser() {
        List<SysUser> sysUserEntityList = userRepository.findAll();
        return R.ok(sysUserEntityList);
    }
}
