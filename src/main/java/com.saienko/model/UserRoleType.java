package com.saienko.model;


/**
 * Created by gleb on 11.11.2015.
 */

public enum UserRoleType {
    USER("USER"),
    DBA("DBA"),
    ADMIN("ADMIN");

    String userRoleType;

    UserRoleType(String userRoleType) {
        this.userRoleType = userRoleType;
    }

    public String getUserRoleType() {
        return userRoleType;
    }
}
