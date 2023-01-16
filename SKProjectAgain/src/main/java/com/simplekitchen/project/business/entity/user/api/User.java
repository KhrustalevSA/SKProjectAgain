package com.simplekitchen.project.business.entity.user.api;

import com.simplekitchen.project.business.entity.recipe.api.Recipe;
import com.simplekitchen.project.business.entity.user.UserNameImpl;

import java.util.Date;
import java.util.List;

public interface User {
    /**
     * Метод для идентификатора пользователя
     * */
    Long getUuid();

    /**
     * Метод для получения имени пользователя
     * */
    UserNameImpl getUserName();

    /**
     * Метод для получения даты рождения пользователя
     * */
    Date getBirthDate();

    /**
     * Метод для получения пола пользователя
     * */
    String getSex();

    /**
     * Метод для получения списка любимых рецептов пользователя
     * */
    List<Recipe> getFavoriteRecipeList();

    /**
     * Метод для получения места жительства пользователя
     * */
    City getCity();


}
