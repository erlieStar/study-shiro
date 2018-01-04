package com.makenv.service;

import com.makenv.entity.ResourceEntity;
import com.makenv.entity.RoleEntity;

import javax.management.relation.Role;
import java.util.List;
import java.util.Set;

public interface SysRoleService {

    public List<RoleEntity> findAll();

    public RoleEntity findOne(long id);

    public int deleteRole(long id);

    public int createRole(RoleEntity roleEntity);

    public int updateRole(RoleEntity roleEntity);

}
