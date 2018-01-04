package com.makenv.service;

import com.makenv.entity.OrganEntity;
import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;

import java.util.List;
import java.util.Set;

public interface SysOrganService {

    public List<OrganEntity> findAll();

    public OrganEntity findOne(long id);

    public int createOrgan(OrganEntity organEntity);

    public int deleteOrgan(long id);

    public int updateOrgan(OrganEntity organEntity);
}
