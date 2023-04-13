package com.simplekitchen.project.business.security.jwt;

import com.simplekitchen.project.dao.entity.entityStatus.EntityStatus;
import com.simplekitchen.project.dao.entity.role.RoleImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRoleList(),
                user.getName(), user.getSurname(), user.getPatronymic(), user.getBirthDate(), user.getSex(),
                user.getFavoriteRecipeList(), user.getCity(), user.getEntityStatus().equals(EntityStatus.ACTIVE),
                user.getLastPasswordResetDate(), mapToGrantedAuthorities(new ArrayList<>(user.getRoleList()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<RoleImpl> userRoles) {
        return userRoles.stream()
                .map(role ->
                    new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
