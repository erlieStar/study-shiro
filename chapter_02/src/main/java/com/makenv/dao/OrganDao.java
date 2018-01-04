package com.makenv.dao;

import com.makenv.entity.OrganEntity;

import java.util.List;

public interface OrganDao {

    List<OrganEntity> findAll();

    OrganEntity findOne(long id);

    int createOrgan(OrganEntity organEntity);

    int deleteOrgan(long id);

    int updateOrgan(OrganEntity organEntity);

}
