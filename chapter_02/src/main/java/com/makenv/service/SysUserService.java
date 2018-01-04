package com.makenv.service;

import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface SysUserService {

    public UserEntity findByUsername(String username);

    public Set<String> findPermissions(long userId);

    public List<UserEntity> findAll();

    public UserEntity findOne(long id);

    public int deleteUser(long id);

    public int createUser(UserEntity userEntity);

    public int updateUser(UserEntity userEntity);

    public int changePassword(long id, String newPassword);
}
