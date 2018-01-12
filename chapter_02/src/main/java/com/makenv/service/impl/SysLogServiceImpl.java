package com.makenv.service.impl;

import com.makenv.dao.LogDao;
import com.makenv.entity.LogEntity;
import com.makenv.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    LogDao logDao;

    public List<LogEntity> findAll() {
        return logDao.findAll();
    }

    public int createLog(LogEntity logEntity) {
        return logDao.createLog(logEntity);
    }
}
