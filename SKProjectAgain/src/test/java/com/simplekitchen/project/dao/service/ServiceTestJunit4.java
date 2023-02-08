package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.city.CityNameEntityImpl;
import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ServiceTestJunit4 {

    private static final String db_driver = "org.h2.Driver";
//    private static final Service<RecipeEntityImpl> recipeService = new RecipeServiceImpl();
//    private static Service<IngredientEntityImpl> ingredientService = new IngredientServiceImpl();
//    private static UserRepository repository;
//    private static UserControllerServiceImpl userService;

    @Test
    public void save() {
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
                .imagesList(null).cookingTime(50L).author("Author").publishDate(new GregorianCalendar(1998, 05, 23)).stepsDescription(null).difficulty("easy")
                .userList(userList).build();

        UserEntityImpl user = UserEntityImpl.builder().id(1L)
                .name("Ivan").surname("Ivanov").patronymic("Ivanovich").sex("M")
                .build();

        ingredientList.add(ingredientPasta);
        ingredientList.add(ingredientTomatoes);
        recipeList.add(recipe);

        //userService.save(user);
        userList.add(user);

       Assert.assertEquals(userList.get(0),user);
    }
}