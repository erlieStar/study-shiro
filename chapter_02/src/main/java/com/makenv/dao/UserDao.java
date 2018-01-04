package com.makenv.dao;

import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;

import java.util.List;

public interface UserDao {

    UserEntity findByUsername(String username);

    List<RoleEntity> findRoles(int userId);

    List<UserEntity> findAll();

    UserEntity findOne(long id);

    int deleteUser(long id);

    int createUser(UserEntity userEntity);

    int updateUser(UserEntity userEntity);
}
