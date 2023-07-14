package cn.codeyang.pter.web.controller;

import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.module.user.entity.SysUser;
import cn.codeyang.pter.module.user.mapper.SysUserMapper;
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
    private SysUserMapper userMapper;

    @GetMapping("/info")
    public String info() {
        return "info";
    }

    @GetMapping("/users")
    public R<List<SysUser>> listUser() {
        List<SysUser> sysUserEntityList = userMapper.list();
        return R.ok(sysUserEntityList);
    }
}
