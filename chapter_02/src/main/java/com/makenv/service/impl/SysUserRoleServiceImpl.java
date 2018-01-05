package com.makenv.service.impl;

import com.makenv.dao.UserDao;
import com.makenv.dao.UserRoleDao;
import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;
import com.makenv.entity.vo.UserVo;
import com.makenv.service.SysUserRoleService;
import com.makenv.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordUtil passwordUtil;

    public List<RoleEntity> findRoles(long userId) {
        return userRoleDao.findRoles(userId);
    }

    public List<Long> findRoleIds(long userId) {
        List<RoleEntity> list = findRoles(userId);
        List<Long> tempList = new ArrayList<Long>();
        for (RoleEntity role : list) {
            tempList.add(role.getId());
        }
        return tempList;
    }

    public int createUserVo(UserVo userVo) {
        UserEntity userEntity = new UserEntity(userVo);
        passwordUtil.encryptPassword(userEntity);
        userDao.createUser(userEntity);

        //创建用户和角色的关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userEntity.getId());
        map.put("roleIdList", userVo.getRoleIdList());
        return userRoleDao.saveOrUpdateUser(map);
    }

    public int updateUserVo(UserVo userVo) {
        UserEntity userEntity = new UserEntity(userVo);
        userDao.updateUser(userEntity);

        //先删除关系
        userRoleDao.deleteUser(userEntity.getId());
        //创建用户和角色的关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userEntity.getId());
        map.put("roleIdList", userVo.getRoleIdList());
        return userRoleDao.saveOrUpdateUser(map);
    }


    public int deleteUserVo(long userId) {
        userDao.deleteUser(userId);
        return userRoleDao.deleteUser(userId);
    }
}
