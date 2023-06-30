package cn.codeyang.pter.web.controller;

import cn.codeyang.pter.common.core.domain.model.LoginBody;
import cn.codeyang.pter.common.core.util.R;
import cn.codeyang.pter.common.utils.SecurityUtils;
import cn.codeyang.pter.module.menu.entity.SysMenu;
import cn.codeyang.pter.module.user.dto.LoginRsp;
import cn.codeyang.pter.module.user.dto.RouterRspDto;
import cn.codeyang.pter.module.user.entity.SysUser;
import cn.codeyang.pter.module.user.service.impl.LoginService;
import cn.codeyang.pter.module.user.service.impl.PermissionService;
import cn.hutool.core.lang.tree.Tree;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yangzy
 */
@RestController
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final PermissionService permissionService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public R<LoginRsp> login(@RequestBody LoginBody loginBody) {
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        LoginRsp loginRsp = new LoginRsp(token);
        return R.ok(loginRsp);
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("info")
    public R<Map<String, Object>> getInfo() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roles", roles);
        map.put("permissions", permissions);
        return R.ok(map);
    }

    @GetMapping("/getRouters")
    public R<List<RouterRspDto>> getRouters() {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<SysMenu> menuPermission = permissionService.selectMenuTreeByUserId(user);
        return R.ok(permissionService.buildMenus(menuPermission));
    }
}
