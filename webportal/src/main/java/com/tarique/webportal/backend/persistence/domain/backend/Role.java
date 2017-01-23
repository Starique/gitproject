package com.tarique.webportal.backend.persistence.domain.backend;

import com.tarique.webportal.enums.RoleEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Mehnuma on 1/12/2017.
 */
@Entity
public class Role implements Serializable{
    /** The serial Version UID for Serialazable classes. */
    private static final long serialVersionUID = 1L;


    @Id
    private int id;

    private String name;

    public Role() {
    }
    public Role(RoleEnum roleEnum){
        id   = roleEnum.getId();
        name = roleEnum.getRoleName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * We are making the fetch type lazy because if we want to delete a user role
     * We don't want the application to to try and dlete a role too
     */

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return id == role.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
