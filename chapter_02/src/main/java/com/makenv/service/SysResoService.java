package com.makenv.service;

import com.makenv.entity.ResourceEntity;

import java.util.List;
import java.util.Set;

public interface SysResoService {

    public List<ResourceEntity> findMenu(Set<String> set);

    public List<ResourceEntity> findAll();

    public int deleteResource(long resourceId);

    public ResourceEntity findOne(long resourceId);

    public int createResource(ResourceEntity resourceEntity);

    public int updateResource(ResourceEntity resourceEntity);

}
