package com.makenv.entity;


import com.makenv.entity.vo.UserVo;

public class UserEntity {

    private long id;
    private String username;
    private String password;
    private String salt;
    private int available;

    public UserEntity() {
    }

    public UserEntity(UserVo userVo) {
        this.id = userVo.getId();
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.salt = userVo.getSalt();
        this.available = userVo.getAvailable();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
