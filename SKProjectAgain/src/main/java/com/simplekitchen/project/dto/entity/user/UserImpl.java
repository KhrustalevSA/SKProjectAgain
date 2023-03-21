package com.simplekitchen.project.dto.entity.user;

import com.simplekitchen.project.dto.entity.city.CityImpl;
import com.simplekitchen.project.dto.entity.city.api.City;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

/**
 * Класс реализовывающий интерфейс UserEntity
 * @see User
 * @author  KhrustalevSA
 * @since 28.09.2022
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserImpl implements User {


    /**
     * уникальный идентификатор пользователя
     */
    private Long id;

    /**
     * имя пользователя
     */
    private String name;

    /**
     * фамилия  пользователя
     */
    private String surname;

    /**
     * отчество пользователя
     */
    private String patronymic;

    /**
     * дата рождения пользователя
     */
    private Calendar birthDate;

    /**
     * пол пользователя
     */
    private String sex;

    /**
     * список рецептов добавленных в "избранное"
     */
    private List<RecipeImpl> favoriteRecipeList;

    /**
     * город, место жительства пользователя
     */
    private CityImpl city;
}
