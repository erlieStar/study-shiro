package com.makenv.dao;

import com.makenv.entity.LogEntity;

import java.util.List;

public interface LogDao {
    public List<LogEntity> findAll();
    int createLog(LogEntity logEntity);
}
