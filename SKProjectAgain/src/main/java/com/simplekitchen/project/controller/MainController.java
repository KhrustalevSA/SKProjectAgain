package com.simplekitchen.project.controller;

import com.simplekitchen.project.dao.entity.city.CityNameEntityImpl;
import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * РЕСТ контроллер для работы с основной частью приложения
 */
@RestController
public class MainController {

//    private static Service<RecipeEntityImpl> recipeService;
//    private static Service<IngredientEntityImpl> ingredientService;
//    private static Service<UserEntityImpl> userService;


    @PostMapping("/getUserBusinessEntity")
    public UserEntity getUser() {
        List<UserEntityImpl> userList = new ArrayList<>();
        List<RecipeEntityImpl> recipeList = new ArrayList<>();
        List<IngredientEntityImpl> ingredientList = new ArrayList<>();
        CityEntityImpl city = CityEntityImpl.builder()
                .cityName(CityNameEntityImpl.builder().cityName("Vologda").build()).regionName("Vologodskaya obl").streetName("Gor val")
                .houseNumber(26L).entranceNumber(1L).flatNumber(112L).build();
        IngredientEntityImpl ingredientPasta = IngredientEntityImpl.builder().id(1L).name("Pasta").averageWeight(300D).expirationDate(900D)
                .expirationDateInFridge(9000D).build();
        IngredientEntityImpl ingredientTomatoes = IngredientEntityImpl.builder().id(2L).name("Tomatoes").averageWeight(0.2D).expirationDate(500D)
                .expirationDateInFridge(150D).build();
        RecipeEntityImpl recipe = RecipeEntityImpl.builder().id(1L).name("PastaWithTomatoes").ingredientsList(ingredientList).description("Delicios pasta with tomatoes!")
                .imagesList(null).cookingTime(50L).author("Author").publishDate(new GregorianCalendar(1998, Calendar.FEBRUARY, 23)).stepsDescription(null).difficulty("easy")
                .userList(userList).build();
        UserEntityImpl user = UserEntityImpl.builder().id(1L)
                .name("Ivan").surname("Ivanov").patronymic("Ivanovich").sex("M").birthDate(new GregorianCalendar(2001, Calendar.FEBRUARY, 1))
                .build();

        userList.add(user);
        ingredientList.add(ingredientPasta);
        ingredientList.add(ingredientTomatoes);
        recipeList.add(recipe);

//      userService.add(user);
        return user;
//      return userService.find(1L);
    }

}
// Запустить на удаленном хосте (где)
