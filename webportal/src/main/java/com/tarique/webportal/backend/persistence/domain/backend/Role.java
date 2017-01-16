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

    @OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserRole> userRoles = new HashSet<>();

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
