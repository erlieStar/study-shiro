package com.makenv.entity;


import com.makenv.entity.vo.RoleVo;

public class RoleEntity {

    private long id;
    private String roleName;
    private String description;
    private int available;

    public RoleEntity() {
    }

    public RoleEntity(RoleVo roleVo) {
        this.id = roleVo.getId();
        this.roleName = roleVo.getRoleName();
        this.description = roleVo.getDescription();
        this.available = roleVo.getAvailable();
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
}
