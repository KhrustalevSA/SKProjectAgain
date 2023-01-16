package com.simplekitchen.project.dto.entity.user;


import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.user.api.City;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Класс реализовывающий интерфейс User
 * @see com.simplekitchen.project.dto.entity.user.api.User
 * @Author KhrustalevSA
 * @since 28.09.2022
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserImpl implements com.simplekitchen.project.dto.entity.user.api.User {

    /**
     * поле уникального идентификатора пользователя
     */
    private Long uuid;

    /**
     * поле имени пользователя
     */
    private String name;

    /**
     * поле фамилии пользователя
     */
    private String surname;

    /**
     * поле отчества пользователя
     */
    private String patronymic;

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
    private City city;
}
