package cn.codeyang.pter.module.role.service;

import cn.codeyang.pter.module.role.entity.SysRole;

import java.util.List;

/**
 * @author yangzy
 */
public interface SysRoleService {
    /**
     * 通过用户ID，查询角色信息
     * @param userId
     * @return
     */
    List<SysRole> findRolesByUserId(Long userId);
}
