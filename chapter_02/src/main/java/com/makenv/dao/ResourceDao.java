package com.makenv.dao;

import com.makenv.entity.ResourceEntity;

import java.util.List;

public interface ResourceDao {

    List<ResourceEntity> findAll();

    int deleteResource(long id);

    ResourceEntity findOne(long id);

    int createResource(ResourceEntity resourceEntity);

    int updateResource(ResourceEntity resourceEntity);
}
