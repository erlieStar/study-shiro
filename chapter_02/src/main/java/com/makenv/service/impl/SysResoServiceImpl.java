package com.makenv.service.impl;

import com.makenv.dao.ResourceDao;
import com.makenv.entity.ResourceEntity;
import com.makenv.service.SysResoService;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class SysResoServiceImpl implements SysResoService {

    @Autowired
    ResourceDao resourceDao;

    public List<ResourceEntity> findMenu(Set<String> permissions) {
        List<ResourceEntity> list = findAll();
        List<ResourceEntity> tempList = new ArrayList<ResourceEntity>();
        for (ResourceEntity resource : list) {
            //根结点，跳过
            if (resource.isRootNode()) {
                continue;
            }
            //不是菜单，跳过
            if (resource.getType() != ResourceEntity.ResourceType.menu) {
                continue;
            }
            //没有权限，跳过
            if (!hasPermission(permissions, resource)) {
                continue;
            }
            tempList.add(resource);
        }
        return tempList;
    }

    public List<ResourceEntity> findAll() {
        return resourceDao.findAll();
    }

    public int deleteResource(long resourceId) {
        return resourceDao.deleteResource(resourceId);
    }

    public ResourceEntity findOne(long resourceId) {
        return resourceDao.findOne(resourceId);
    }

    public int createResource(ResourceEntity resourceEntity) {
        return resourceDao.createResource(resourceEntity);
    }

    public int updateResource(ResourceEntity resourceEntity) {
        return resourceDao.updateResource(resourceEntity);
    }

    /**
     * @Author : lilimin
     * @Description : 通过比对权限字符串来看是否具有权限
     * @Date : Created in 18:39 2017/12/27
     */
    public boolean hasPermission(Set<String> permissions, ResourceEntity resource) {

        //处理最顶上一级'资源'的权限为空的情况
        if (StringUtils.isEmpty(resource.getPermission())) {
            return true;
        }

        for (String permission : permissions) {
            WildcardPermission p1 = new WildcardPermission(permission);
            WildcardPermission p2 = new WildcardPermission(resource.getPermission());
            if (p1.implies(p2) || p2.implies(p1)) {
                return true;
            }
        }
        return false;
    }
}
