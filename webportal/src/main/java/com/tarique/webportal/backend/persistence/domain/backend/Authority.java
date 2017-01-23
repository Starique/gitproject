package com.tarique.webportal.backend.persistence.domain.backend;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Mehnuma on 1/22/2017.
 * Granted Authority is a Spring class that encapsulate the role
 *
 */
public class Authority implements GrantedAuthority {

    private final String authority;

    public Authority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}
