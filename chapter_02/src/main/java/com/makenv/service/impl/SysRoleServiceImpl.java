package com.makenv.service.impl;

import com.makenv.dao.RoleDao;
import com.makenv.entity.ResourceEntity;
import com.makenv.entity.RoleEntity;
import com.makenv.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    RoleDao roleDao;

    public List<RoleEntity> findAll() {
        return roleDao.findAll();
    }

    public RoleEntity findOne(long id) {
        return roleDao.findOne(id);
    }

    public int deleteRole(long id) {
        return roleDao.deleteRole(id);
    }

    public int createRole(RoleEntity roleEntity) {
        return roleDao.createRole(roleEntity);
    }

    public int updateRole(RoleEntity roleEntity) {
        return roleDao.updateRole(roleEntity);
    }

}
