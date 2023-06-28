package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.module.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangzy
 */
@Component
@RequiredArgsConstructor
public class PermissionService {

    public Set<String> getRolePermission(User user) {
        Set<String> roles = new HashSet<>();
        roles.add("admin");
        return roles;
    }

    public Set<String> getMenuPermission(User user) {
        Set<String> menus = new HashSet<>();
        menus.add("*:*:*");
        return menus;
    }
}
