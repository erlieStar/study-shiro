package com.makenv.dao;

import com.makenv.entity.ResourceEntity;
import com.makenv.entity.RoleEntity;

import javax.management.relation.Role;
import java.util.List;

public interface RoleDao {

    List<RoleEntity> findAll();

    RoleEntity findOne(long id);

    List<ResourceEntity> findPermissions(long id);

    int deleteRole(long id);

    int createRole(RoleEntity roleEntity);

    int updateRole(RoleEntity roleEntity);
}
