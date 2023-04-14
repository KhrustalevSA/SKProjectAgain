package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.security.SecurityRole;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;

import java.util.Calendar;
import java.util.List;

/**
 * интерфейс сущности пользователя
 * @author KhrustalevSA
 * @since 22.01.2023
 */
public interface UserEntity {

    /**
     * @return идентификатор пользователя
     */
    Long getId();

    /**
     * @return имя пользователя
     * */
    String getName();

    /**
     * @return фамилия пользователя
     * */
    String getSurname();

    String getLogin();

    String getPassword();

    SecurityRole getRole();

    /**
     * @return отчество пользователя
     * */
    String getPatronymic();

    /**
     * @return дата рождения пользователя
     * */
    Calendar getBirthDate();

    /**
     * @return пол пользователя
     * */
    String getSex();

    /**
     * @return список любимых рецептов пользователя
     * */
    List<RecipeEntityImpl> getFavoriteRecipeList();

    /**
     * @return город, место жительства пользователя
     * */
    CityEntityImpl getCity();
}
