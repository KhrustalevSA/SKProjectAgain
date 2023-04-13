package com.simplekitchen.project.business.security.jwt;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.role.RoleImpl;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

public class JwtUser implements UserDetails {


    private final Long id;

    private final String username;

    private final String email;

    private final String password;

    private final List<RoleImpl> roles;

    private final String name;

    private final String surname;

    private final String patronymic;

    private final Calendar birthDate;

    private final String sex;

    private final List<RecipeEntityImpl> favoriteRecipeList;

    private final CityEntityImpl city;

    private final boolean enabled;

    private final Calendar lastPasswordResetDate;

    private final Collection<? extends GrantedAuthority> authorities;

    public JwtUser(Long id, String username, String email, String password, List<RoleImpl> roles, String name,
                   String surname, String patronymic, Calendar birthDate, String sex, List<RecipeEntityImpl> favoriteRecipeList,
                   CityEntityImpl city, boolean enabled, Calendar lastPasswordResetDate,
                   Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.favoriteRecipeList = favoriteRecipeList;
        this.city = city;
        this.enabled = enabled;
        this.lastPasswordResetDate = lastPasswordResetDate;
        this.authorities = authorities;
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public List<RoleImpl> getRoles() {
        return roles;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Calendar getBirthDate() {
        return birthDate;
    }

    public String getSex() {
        return sex;
    }

    public List<RecipeEntityImpl> getFavoriteRecipeList() {
        return favoriteRecipeList;
    }

    public CityEntityImpl getCity() {
        return city;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @JsonIgnore
    public Calendar getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
