package com.simplekitchen.project.business.entity.user;

import com.simplekitchen.project.business.entity.recipe.api.Recipe;
import com.simplekitchen.project.business.entity.user.api.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * Класс для создания объекта пользователя
 * @author Khrustalev-sa
 * @since 26.10.2022
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserImpl implements User {

    /**
     * уникальный идентификатор пользователя
     */
    private Long uuid;

    private UserNameImpl userName;

    /**
     * поле возраста пользователя
     */
    private Date birthDate;

    /**
     * поле хранит пол пользователя
     */
    private String sex;

    /**
     * поле хранит список избранных рецептов пользователя
     */
    private List<Recipe> favoriteRecipeList;

    /**
     * поле хранит место жительства пользователя
     */
    private CityImpl city;


}
