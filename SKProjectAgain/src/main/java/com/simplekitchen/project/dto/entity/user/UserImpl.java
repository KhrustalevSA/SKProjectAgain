package com.simplekitchen.project.dto.entity.user;

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
 * Класс реализовывающий интерфейс User
 * @see User
 * @Author KhrustalevSA
 * @since 28.09.2022
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserImpl implements User {

    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private Calendar birthDate;

    private String sex;

    private List<RecipeImpl> favoriteRecipeList;

    private City city;
}
