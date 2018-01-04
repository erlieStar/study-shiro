package com.makenv.service.impl;

import com.makenv.dao.RoleDao;
import com.makenv.dao.RoleResourceDao;
import com.makenv.entity.ResourceEntity;
import com.makenv.entity.RoleEntity;
import com.makenv.entity.vo.RoleVo;
import com.makenv.service.SysRoleResoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysRoleResoServiceImpl implements SysRoleResoService {

    @Autowired
    RoleResourceDao roleResourceDao;

    @Autowired
    RoleDao roleDao;

    /**
     * @Author : lilimin
     * @Description : 根据用户Id查找资源
     * @Date : Created in 14:10 2018/1/3
     */
    public List<ResourceEntity> findResource(long roleId) {
        return roleResourceDao.findResource(roleId);
    }

    public List<Long> findResourceIds(long roleId) {
        List<ResourceEntity> resourceList = findResource(roleId);
        List<Long> list = new ArrayList<Long>();
        for (ResourceEntity resourceEntity : resourceList) {
            list.add(resourceEntity.getId());
        }
        return list;
    }

    /**
     * @Author : lilimin
     * @Description : 根据用户Id查找资源字符串
     * @Date : Created in 14:12 2018/1/3
     */
    public Set<String> findPermissions(long roleId) {
        List<ResourceEntity> list = roleResourceDao.findResource(roleId);
        Set<String> set = new HashSet<String>();
        for (ResourceEntity resource : list) {
            set.add(resource.getPermission());
        }
        return set;
    }

    public int createRoleVo(RoleVo roleVo) {

        RoleEntity roleEntity = new RoleEntity(roleVo);
        roleDao.createRole(roleEntity);

        //创建用户和资源的关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleEntity.getId());
        map.put("resourceIdList", roleVo.getResourceIdList());
        return roleResourceDao.saveOrUpdateRole(map);

    }

    public int updateRoleVo(RoleVo roleVo) {
        RoleEntity roleEntity = new RoleEntity(roleVo);
        roleDao.updateRole(roleEntity);

        //删除关系
        roleResourceDao.deleteRole(roleEntity.getId());
        //创建用户和资源的关系
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleId", roleEntity.getId());
        map.put("resourceIdList", roleVo.getResourceIdList());
        return roleResourceDao.saveOrUpdateRole(map);
    }

    public int deleteRoleVo(long roleId) {
        roleDao.deleteRole(roleId);
        return roleResourceDao.deleteRole(roleId);
    }

}
