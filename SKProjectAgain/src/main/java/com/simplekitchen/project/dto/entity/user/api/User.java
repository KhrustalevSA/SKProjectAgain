package com.simplekitchen.project.dto.entity.user.api;


import com.simplekitchen.project.dto.entity.recipe.api.Recipe;

import java.util.Date;
import java.util.List;

/**
 *Интерфейс объекта Пользователь
 * @Author Khrustalev-sa
 * @since 28.09.2022
 * */
public interface User {

    /**
     * Метод для получения имени пользователя
     * */
    Long getUuid();

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
    List<Recipe> getFavoriteRecipeList();

    /**
     * Метод для получения места жительства пользователя
     * */
    City getCity();
}
