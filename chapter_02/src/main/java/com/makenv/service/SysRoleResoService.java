package com.makenv.service;

import com.makenv.entity.ResourceEntity;
import com.makenv.entity.RoleEntity;
import com.makenv.entity.vo.RoleVo;
import com.makenv.entity.vo.UserVo;

import java.util.List;
import java.util.Set;

public interface SysRoleResoService {

    public List<ResourceEntity> findResource(long roleId);
    public List<Long> findResourceIds(long roleId);
    public Set<String> findPermissions(long roleId);
    public int createRoleVo(RoleVo roleVo);
    public int updateRoleVo(RoleVo roleVo);
    public int deleteRoleVo(long roleId);
}
