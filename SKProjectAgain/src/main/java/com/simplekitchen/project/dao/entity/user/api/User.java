package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.Date;
import java.util.List;

public interface User {
    /**
     * Метод для получения имени пользователя
     * */
    Long getId();

    /**
     * Метод для получения имени пользователя
     * */
    String getName();

    /**
     * Метод для получения фамилии пользователя
     * */
    String getSurname();

    /**
     * Метод для получения отчества пользователя
     * */
    String getPatronymic();

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
    List<RecipeImpl> getFavoriteRecipeList();

    /**
     * Метод для получения места жительства пользователя
     * */
    City getCity();
}
