package cn.codeyang.pter.module.user.service;

import cn.codeyang.pter.module.user.entity.SysUser;

/**
 * @author yangzy
 */
public interface SysUserService {
    /**
     * 通过用户名查找用户信息
     * @param username 用户名
     * @return User对象
     */
    SysUser selectByUsername(String username);

    /**
     * 更新用户profile信息
     * @param user
     */
    void updateUserProfile(SysUser user);
}
