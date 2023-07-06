package cn.codeyang.pter.framework.security.service;

import cn.codeyang.pter.common.core.constant.CommonConstants;
import cn.codeyang.pter.common.core.constant.UserConstants;
import cn.codeyang.pter.common.utils.SecurityUtils;
import cn.codeyang.pter.module.system.entity.SysMenu;
import cn.codeyang.pter.module.system.service.SysMenuService;
import cn.codeyang.pter.module.system.entity.SysRole;
import cn.codeyang.pter.module.system.dto.MetaDto;
import cn.codeyang.pter.module.system.dto.RouterRspDto;
import cn.codeyang.pter.module.system.entity.SysUser;
import cn.codeyang.pter.module.system.repository.SysUserRepository;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.http.HttpUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangzy
 */
@Component
@RequiredArgsConstructor
public class PermissionService {
    private final SysUserRepository userRepository;
    private final SysMenuService sysMenuService;

    @Transactional(rollbackFor = Exception.class)
    public Set<String> getRolePermission(Long userId) {
        Optional<SysUser> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            Set<SysRole> roles = optional.get().getRoles();
            if (CollUtil.isNotEmpty(roles)) {
                return roles.stream().map(SysRole::getRoleName).collect(Collectors.toSet());
            }
        }

        return new HashSet<>();
    }

    public Set<String> getMenuPermission(Long userId) {
        Set<String> perms = new HashSet<>();

        Optional<SysUser> optional = userRepository.findById(userId);
        if (optional.isPresent()) {
            SysUser user = optional.get();
            if (SecurityUtils.isAdmin(user.getId())) {
                perms.add("*:*:*");
            } else {
                Set<SysRole> roles = user.getRoles();
                roles.forEach(role -> {
                    perms.addAll(role.getMenus().stream().map(SysMenu::getPerms).collect(Collectors.toSet()));
                });
            }
        }

        return perms;
    }

    public List<SysMenu> selectMenuTreeByUserId(SysUser user) {
        List<SysMenu> menus = null;
        if (SecurityUtils.isAdmin(user.getId())) {
            menus = sysMenuService.selectMenuTreeAll();
        } else {
            menus = sysMenuService.selectMenuTreeByUser(user);
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
            if (t.getParent().getId() == parentId) {
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
            if (n.getParent().getId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    public List<RouterRspDto> buildMenus(List<SysMenu> menuPermission) {
        List<RouterRspDto> routers = new LinkedList<>();
        for (SysMenu menu : menuPermission) {
            RouterRspDto router = new RouterRspDto();
            router.setHidden("1".equals(menu.getVisible()));
            router.setName(getRouteName(menu));
            router.setPath(getRouterPath(menu));
            router.setComponent(getComponent(menu));
            router.setQuery(menu.getQuery());
            router.setMeta(new MetaDto(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
            List<SysMenu> cMenus = menu.getChildren();
            if (CollUtil.isNotEmpty(cMenus) && UserConstants.TYPE_DIR.equals(menu.getMenuType())) {
                router.setAlwaysShow(true);
                router.setRedirect("noRedirect");
                router.setChildren(buildMenus(cMenus));
            } else if (isMenuFrame(menu)) {
                router.setMeta(null);
                List<RouterRspDto> childrenList = new ArrayList<>();
                RouterRspDto children = new RouterRspDto();
                children.setPath(menu.getPath());
                children.setComponent(menu.getComponent());
                children.setName(StringUtils.capitalize(menu.getPath()));
                children.setMeta(new MetaDto(menu.getMenuName(), menu.getIcon(), StringUtils.equals("1", menu.getIsCache()), menu.getPath()));
                children.setQuery(menu.getQuery());
                childrenList.add(children);
                router.setChildren(childrenList);
            } else if (menu.getParent().getId().intValue() == 0 && isInnerLink(menu)) {
                router.setMeta(new MetaDto(menu.getMenuName(), menu.getIcon()));
                router.setPath("/");
                List<RouterRspDto> childrenList = new ArrayList<>();
                RouterRspDto children = new RouterRspDto();
                String routerPath = innerLinkReplaceEach(menu.getPath());
                children.setPath(routerPath);
                children.setComponent(UserConstants.INNER_LINK);
                children.setName(StringUtils.capitalize(routerPath));
                children.setMeta(new MetaDto(menu.getMenuName(), menu.getIcon(), menu.getPath()));
                childrenList.add(children);
                router.setChildren(childrenList);
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取组件信息
     *
     * @param menu 菜单信息
     * @return 组件信息
     */
    public String getComponent(SysMenu menu) {
        String component = UserConstants.LAYOUT;
        if (StringUtils.isNotEmpty(menu.getComponent()) && !isMenuFrame(menu)) {
            component = menu.getComponent();
        } else if (StringUtils.isEmpty(menu.getComponent()) && menu.getParent().getId().intValue() != 0 && isInnerLink(menu)) {
            component = UserConstants.INNER_LINK;
        } else if (StringUtils.isEmpty(menu.getComponent()) && isParentView(menu)) {
            component = UserConstants.PARENT_VIEW;
        }
        return component;
    }

    /**
     * 是否为parent_view组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isParentView(SysMenu menu) {
        return menu.getParent().getId().intValue() != 0 && UserConstants.TYPE_DIR.equals(menu.getMenuType());
    }

    /**
     * 获取路由名称
     *
     * @param menu 菜单信息
     * @return 路由名称
     */
    public String getRouteName(SysMenu menu) {
        String routerName = StringUtils.capitalize(menu.getPath());
        // 非外链并且是一级目录（类型为目录）
        if (isMenuFrame(menu)) {
            routerName = StringUtils.EMPTY;
        }
        return routerName;
    }

    /**
     * 是否为菜单内部跳转
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isMenuFrame(SysMenu menu) {
        return menu.getParent().getId().intValue() == 0 && UserConstants.TYPE_MENU.equals(menu.getMenuType())
                && menu.getIsFrame().equals(UserConstants.NO_FRAME);
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    public String getRouterPath(SysMenu menu) {
        String routerPath = menu.getPath();
        // 内链打开外网方式
        if (menu.getParent().getId().intValue() != 0 && isInnerLink(menu)) {
            routerPath = innerLinkReplaceEach(routerPath);
        }
        // 非外链并且是一级目录（类型为目录）
        if (0 == menu.getParent().getId().intValue() && UserConstants.TYPE_DIR.equals(menu.getMenuType())
                && UserConstants.NO_FRAME.equals(menu.getIsFrame())) {
            routerPath = "/" + menu.getPath();
        }
        // 非外链并且是一级目录（类型为菜单）
        else if (isMenuFrame(menu)) {
            routerPath = "/";
        }
        return routerPath;
    }

    /**
     * 是否为内链组件
     *
     * @param menu 菜单信息
     * @return 结果
     */
    public boolean isInnerLink(SysMenu menu) {
        return menu.getIsFrame().equals(UserConstants.NO_FRAME) && HttpUtil.isHttp(menu.getPath());
    }

    /**
     * 内链域名特殊字符替换
     *
     * @return
     */
    public String innerLinkReplaceEach(String path) {
        return StringUtils.replaceEach(path, new String[]{CommonConstants.HTTP, CommonConstants.HTTPS},
                new String[]{"", ""});
    }
}
