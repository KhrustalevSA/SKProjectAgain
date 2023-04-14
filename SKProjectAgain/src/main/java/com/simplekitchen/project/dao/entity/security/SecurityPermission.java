package com.simplekitchen.project.dao.entity.security;

public enum SecurityPermission {
    USER_READ("user:read"),
    USER_WRITE("user:write");

    private final String permission;

    SecurityPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
