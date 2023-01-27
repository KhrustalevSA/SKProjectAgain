package com.simplekitchen.project.controller;

import com.simplekitchen.project.dao.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dao.entity.city.CityNameImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class MainController {

//    private static Service<RecipeImpl> recipeService;
//    private static Service<IngredientImpl> ingredientService;
//    private static Service<UserImpl> userService;


    @PostMapping("/getUserBusinessEntity")
    public User getUser() {
        List<UserImpl> userList = new ArrayList<>();
        List<RecipeImpl> recipeList = new ArrayList<>();
        List<IngredientImpl> ingredientList = new ArrayList<>();
        CityImpl city = CityImpl.builder()
                .cityName(CityNameImpl.builder().cityName("Vologda").build()).regionName("Vologodskaya obl").streetName("Gor val")
                .houseNumber(26L).entranceNumber(1L).flatNumber(112L).build();
        IngredientImpl ingredientPasta = IngredientImpl.builder().id(1L).name("Pasta").averageWeight(300D).expirationDate(900D)
                .expirationDateInFridge(9000D).build();
        IngredientImpl ingredientTomatoes = IngredientImpl.builder().id(2L).name("Tomatoes").averageWeight(0.2D).expirationDate(500D)
                .expirationDateInFridge(150D).build();
        RecipeImpl recipe = RecipeImpl.builder().id(1L).name("PastaWithTomatoes").ingredientsList(ingredientList).description("Delicios pasta with tomatoes!")
                .imagesList(null).cookingTime(50L).author("Author").publishDate(new GregorianCalendar(1998, Calendar.FEBRUARY, 23)).stepsDescription(null).difficulty("easy")
                .userList(userList).build();
        UserImpl user = UserImpl.builder().id(1L)
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
