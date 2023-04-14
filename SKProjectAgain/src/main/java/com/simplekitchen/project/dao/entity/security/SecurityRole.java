package com.simplekitchen.project.dao.entity.security;

import org.assertj.core.util.Lists;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public enum SecurityRole {
    USER(Lists.newArrayList(SecurityPermission.USER_READ)),
    ADMIN(Lists.newArrayList(SecurityPermission.USER_WRITE, SecurityPermission.USER_READ));

    private final List<SecurityPermission> permissions;

    SecurityRole(List<SecurityPermission> permissions) {
        this.permissions = permissions;
    }

    public List<SecurityPermission> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities() {
        return getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }
}
