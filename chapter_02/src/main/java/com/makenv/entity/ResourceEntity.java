package com.makenv.entity;

public class ResourceEntity {

    private long id;
    private String name;
    private ResourceType type;
    private String url;
    private int priority;
    private long parentId;
    private String permission;
    private int available;

    public static enum ResourceType {

        menu("菜单"), button("按钮");

        private String value;

        private ResourceType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public boolean isRootNode() {
        return parentId == 0;
    }
}
