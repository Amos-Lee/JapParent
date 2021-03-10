package com.jos.jap.system.po;

import java.util.Date;

public class SysUser {
    private Long userId;
    private String userName;
    private String password;
    private Date creationDate;
    private Long createdBy;
    private Date lastUpdateBy;
    private Long objectVersion;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(Date lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }

    public Long getObjectVersion() {
        return objectVersion;
    }

    public void setObjectVersion(Long objectVersion) {
        this.objectVersion = objectVersion;
    }
}
