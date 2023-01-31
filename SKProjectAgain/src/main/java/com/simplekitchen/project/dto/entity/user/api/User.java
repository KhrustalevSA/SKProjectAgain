package com.simplekitchen.project.dto.entity.user.api;


import com.simplekitchen.project.dto.entity.city.api.City;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

import java.util.Calendar;
import java.util.List;

/**
 * интерфейс ДТО пользователья
 * @author KhrustalevSA
 * @since 28.09.2022
 * */
public interface User {

    /**
     * @return идентификатор пользователя
     */
    Long getId();

    /**
     * @return именя пользователя
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
    City getCity();
}
