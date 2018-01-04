package com.makenv.service.impl;

import com.makenv.dao.UserDao;
import com.makenv.entity.RoleEntity;
import com.makenv.entity.UserEntity;
import com.makenv.service.*;
import com.makenv.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    UserDao userDao;

    @Autowired
    SysRoleResoService sysRoleResoService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    PasswordUtil passwordUtil;

    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    public Set<String> findPermissions(long userId) {
        List<RoleEntity> roleList = sysUserRoleService.findRoles(userId);
        Set<String> set = new HashSet<String>();
        for (RoleEntity role : roleList) {
            Set<String> tempSet = sysRoleResoService.findPermissions(role.getId());
            set.addAll(tempSet);
        }
        return set;
    }

    public List<UserEntity> findAll() {
        return userDao.findAll();
    }

    public UserEntity findOne(long id) {
        return userDao.findOne(id);
    }

    public int deleteUser(long id) {
        return userDao.deleteUser(id);
    }

    public int createUser(UserEntity userEntity) {
        return userDao.createUser(userEntity);
    }

    public int updateUser(UserEntity userEntity) {
        return userDao.updateUser(userEntity);
    }

    public int changePassword(long id, String newPassword) {
        UserEntity userEntity = findOne(id);
        userEntity.setPassword(newPassword);
        passwordUtil.encryptPassword(userEntity);
        return userDao.updateUser(userEntity);
    }
}
