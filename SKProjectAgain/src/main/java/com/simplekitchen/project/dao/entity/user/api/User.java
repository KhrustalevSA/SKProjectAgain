package com.simplekitchen.project.dao.entity.user.api;

import com.simplekitchen.project.dao.entity.city.api.City;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.Calendar;
import java.util.List;

/**
 * Информация о пользователя
 */
public interface User {

    /**
     * метод для получения идентификатора пользователя
     * @return идентификатор пользователя
     */
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
    Calendar getBirthDate();

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
