package com.makenv.entity.vo;

import com.makenv.entity.RoleEntity;

import java.util.List;

public class RoleVo {

    private long id;
    private String roleName;
    private String description;
    private int available;
    private List<Long> resourceIdList;

    public RoleVo() {
    }

    public RoleVo(RoleEntity roleEntity) {
        this.id = roleEntity.getId();
        this.roleName = roleEntity.getRoleName();
        this.description = roleEntity.getDescription();
        this.available = roleEntity.getAvailable();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public List<Long> getResourceIdList() {
        return resourceIdList;
    }

    public void setResourceIdList(List<Long> resourceIdList) {
        this.resourceIdList = resourceIdList;
    }
}
