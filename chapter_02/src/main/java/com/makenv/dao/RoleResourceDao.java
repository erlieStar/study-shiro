package com.makenv.dao;

import com.makenv.entity.ResourceEntity;
import com.makenv.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleResourceDao {

    public List<ResourceEntity> findResource(@Param("id") long roleId);

    public int saveOrUpdateRole(Map<String, Object> map);

    public int deleteRole(@Param("id") long roleId);

    public int deleteResource(@Param("id") long resourceId);

}
