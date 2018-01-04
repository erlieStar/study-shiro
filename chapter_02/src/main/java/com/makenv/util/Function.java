package com.makenv.util;

import com.makenv.entity.RoleEntity;
import com.makenv.service.SysRoleService;
import org.springframework.util.CollectionUtils;

import java.util.Collection;

public class Function {

    public static boolean in(Iterable iterable, Object element) {
        if(iterable == null) {
            return false;
        }
        return CollectionUtils.contains(iterable.iterator(), element);
    }

    public static String roleNames(Collection<Long> roleIds) {
        if(CollectionUtils.isEmpty(roleIds)) {
            return "";
        }

        StringBuilder s = new StringBuilder();
        for(Long roleId : roleIds) {
            RoleEntity role = getRoleService().findOne(roleId);
            if(role == null) {
                return "";
            }
            s.append(role.getDescription());
            s.append(",");
        }

        if(s.length() > 0) {
            s.deleteCharAt(s.length() - 1);
        }

        return s.toString();
    }

    private static SysRoleService roleService;


    public static SysRoleService getRoleService() {
        if(roleService == null) {
            roleService = SpringUtil.getBean(SysRoleService.class);
        }
        return roleService;
    }

}

