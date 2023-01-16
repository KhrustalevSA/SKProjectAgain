package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.IngredientImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.user.CityImpl;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.service.api.Service;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceTestJunit4 {

    private static final String db_driver = "org.h2.Driver";
//    private static final Service<RecipeImpl> recipeService = new RecipeServiceImpl();
//    private static Service<IngredientImpl> ingredientService = new IngredientServiceImpl();
    private static final Service<UserImpl> userService = new UserServiceImpl();

    @Test
    public void add() {
        List<UserImpl> userList = new ArrayList<>();
        List<RecipeImpl> recipeList = new ArrayList<>();
        List<IngredientImpl> ingredientList = new ArrayList<>();
        CityImpl city = CityImpl.builder().uuid(1L)
                .cityName("Vologda").regionName("Vologodskaya obl").streetName("Gor val")
                .houseNumber(26L).entranceNumber(1L).flatNumber(112L).build();
        IngredientImpl ingredientPasta = IngredientImpl.builder().uuid(1L).name("Pasta").recipeList(recipeList).averageWeight(300D).expirationDate(900D)
                .expirationDateInFridge(9000D).build();
        IngredientImpl ingredientTomatoes = IngredientImpl.builder().uuid(2L).name("Tomatoes").recipeList(recipeList).averageWeight(0.2D).expirationDate(500D)
                .expirationDateInFridge(150D).build();
        RecipeImpl recipe = RecipeImpl.builder().uuid(1L).name("PastaWithTomatoes").ingredientsList(ingredientList).description("Delicios pasta with tomatoes!")
                .imagesList(null).cookingTime(50L).author("Author").publishDate(new Date(1998, 05, 23)).stepsDescription(null).difficulty("easy")
                .userList(userList).build();
        UserImpl user = UserImpl.builder().id(1L)
                .name("Ivan").surname("Ivanov").patronymic("Ivanovich").sex("M")
                .build();

        ingredientList.add(ingredientPasta);
        ingredientList.add(ingredientTomatoes);
        recipeList.add(recipe);

        userService.add(user);
        userList.add(user);

        Assert.assertEquals(userList.get(0),userService.find(1L));
    }
}