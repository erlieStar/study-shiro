package com.makenv.service.impl;

import com.makenv.dao.OrganDao;
import com.makenv.entity.OrganEntity;
import com.makenv.service.SysOrganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysOrganServiceImpl implements SysOrganService {

    @Autowired
    OrganDao organDao;

    public List<OrganEntity> findAll() {
        List<OrganEntity> list = organDao.findAll();
        return list;
    }

    public OrganEntity findOne(long id) {
        return organDao.findOne(id);
    }

    public int createOrgan(OrganEntity organEntity) {
        return organDao.createOrgan(organEntity);
    }

    public int deleteOrgan(long id) {
        return organDao.deleteOrgan(id);
    }

    public int updateOrgan(OrganEntity organEntity) {
        return organDao.updateOrgan(organEntity);
    }
}
