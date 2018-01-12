package com.makenv.service;

import com.makenv.entity.LogEntity;
import com.makenv.entity.OrganEntity;
import sun.rmi.runtime.Log;

import java.util.List;

public interface SysLogService {
    public List<LogEntity> findAll();
    public int createLog(LogEntity logEntity);
}
