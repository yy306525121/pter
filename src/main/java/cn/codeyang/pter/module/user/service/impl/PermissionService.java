package cn.codeyang.pter.module.user.service.impl;

import cn.codeyang.pter.common.core.constant.CommonConstants;
import cn.codeyang.pter.common.core.constant.UserConstants;
import cn.codeyang.pter.common.utils.SecurityUtils;
import cn.codeyang.pter.module.menu.entity.SysMenu;
import cn.codeyang.pter.module.menu.service.SysMenuService;
import cn.codeyang.pter.module.role.entity.SysRole;
import cn.codeyang.pter.module.role.service.SysRoleService;
import cn.codeyang.pter.module.user.dto.MetaDto;
import cn.codeyang.pter.module.user.dto.RouterRspDto;
import cn.codeyang.pter.module.user.entity.SysUser;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author yangzy
 */
@Component
@RequiredArgsConstructor
public class PermissionService {
    private final SysRoleService sysRoleService;
    private final SysMenuService sysMenuService;

    public Set<String> getRolePermission(SysUser user) {
        List<SysRole> sysRoleList = sysRoleService.findRolesByUserId(user.getId());
        return sysRoleList.stream().map(SysRole::getRoleKey).collect(Collectors.toSet());
    }

    public Set<String> getMenuPermission(SysUser user) {
        Set<String> perms = new HashSet<>();
        if (SecurityUtils.isAdmin(user.getId())) {
            perms.add("*:*:*");
        } else {
            perms.addAll(sysMenuService.selectMenuPermsByUserId(user.getId()));
        }
        return perms;
    }

    public List<SysMenu> selectMenuTreeByUserId(SysUser user) {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(user.getId())) {
            menus = sysMenuService.selectMenuTreeAll();
        } else {
            menus = sysMenuService.selectMenuTreeByUserId(user.getId());
        }
        return getChildPerms(menus, 0L);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list     分类表
     * @param parentId 传入的父节点ID
     * @return String
     */
    private List<SysMenu> getChildPerms(List<SysMenu> list, Long parentId) {
        List<SysMenu> returnList = new ArrayList<>();
        for (Iterator<SysMenu> iterator = list.iterator(); iterator.hasNext(); ) {
            SysMenu t = iterator.next();
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId() == parentId) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     *
     * @param list
     * @param t
     */
    private void recursionFn(List<SysMenu> list, SysMenu t) {
        // 得到子节点列表
        List<SysMenu> childList = getChildList(list, t);
        t.setChildren(childList);
        for (SysMenu tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }


    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysMenu> list, SysMenu t) {
        return getChildList(list, t).size() > 0;
    }

    /**
     * 得到子节点列表
     */
    private List<SysMenu> getChildList(List<SysMenu> list, SysMenu t) {
        List<SysMenu> tlist = new ArrayList<SysMenu>();
        Iterator<SysMenu> it = list.iterator();
        while (it.hasNext()) {
            SysMenu n = it.next();
            if (n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    public List<Tree<Long>> buildMenus(List<SysMenu> menuPermission) {
        List<RouterRspDto> routers = new LinkedList<>();
        List<TreeNode<Long>> collect = menuPermission.stream().map(getNodeFunction()).collect(Collectors.toList());

        return TreeUtil.build(collect, CommonConstants.MENU_TREE_ROOT_ID);
    }

    private Function<SysMenu, TreeNode<Long>> getNodeFunction() {
        return menu -> {
            TreeNode<Long> node = new TreeNode<>();
            node.setId(menu.getId());
            node.setName(menu.getName());
            node.setParentId(menu.getParentId());

            //扩展属性
            Map<String, Object> extra = new HashMap<>();
            extra.put("path", menu.getPath());
            extra.put("title", menu.getTitle());
            extra.put("icon", menu.getIcon());
            extra.put("badge", menu.getBadge());
            extra.put("target", menu.getTarget());
            extra.put("link", menu.getLink());
            extra.put("component", menu.getComponent());
            extra.put("renderMenu", menu.getRenderMenu());
            extra.put("permission", menu.getPermission());
            node.setExtra(extra);
            return node;
        };
    }

}
