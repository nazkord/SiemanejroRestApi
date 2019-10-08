package com.nazkord.siemajero.security;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

public enum Role {
    ADMIN,
    USER,
    MANAGER;

    //TODO: find out where to put those methods

    public boolean isInAdminRole(SecurityContextHolderAwareRequestWrapper securityWrapper) {
        return securityWrapper.isUserInRole(Role.ADMIN.name());
    }

    public boolean isInUserRole(SecurityContextHolderAwareRequestWrapper securityWrapper) {
        return securityWrapper.isUserInRole(USER.name());
    }

    public boolean isInManagerRole(SecurityContextHolderAwareRequestWrapper securityWrapper) {
        return securityWrapper.isUserInRole(MANAGER.name());
    }
}
