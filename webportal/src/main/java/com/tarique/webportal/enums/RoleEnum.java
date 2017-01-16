package com.tarique.webportal.enums;

/**
 * Created by Mehnuma on 1/15/2017.
 */
public enum RoleEnum {

    BASIC(1, "ROLE_BASIC"), PRO(2, "ROLE_PRO"), ADMIN(3, "ROLE_ADMIN");

    private final int id;
    private final String roleName;

    RoleEnum(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    public String getRoleName() {
        return roleName;
    }
}
