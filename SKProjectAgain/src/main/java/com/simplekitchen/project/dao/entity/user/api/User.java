package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.city.api.City;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.Calendar;
import java.util.List;

/**
 * интерфейс сущности пользователя
 * @author KhrustalevSA
 * @since 22.01.2023
 */
public interface User {

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
    List<RecipeImpl> getFavoriteRecipeList();

    /**
     * @return город, место жительства пользователя
     * */
    CityImpl getCity();
}
