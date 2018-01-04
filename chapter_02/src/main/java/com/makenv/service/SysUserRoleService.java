package com.makenv.service;

import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;
import com.makenv.entity.vo.UserVo;

import java.util.List;
import java.util.Set;

public interface SysUserRoleService {

    public List<RoleEntity> findRoles(long userId);
    public List<Long> findRoleIds(long userId);
    public int createUserVo(UserVo userVo);
    public int updateUserVo(UserVo userVo);
    public int deleteUserVo(long userId);
}
