package com.makenv.dao;

import com.makenv.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserRoleDao {

    public List<RoleEntity> findRoles(@Param("id") long userId);

    public int saveOrUpdateUser(Map<String, Object> map);

    public int deleteUser(@Param("id") long userId);
}
