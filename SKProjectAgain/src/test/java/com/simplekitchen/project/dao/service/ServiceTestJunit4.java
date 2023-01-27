package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dao.entity.city.CityNameImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.service.api.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class ServiceTestJunit4 {

    private static final String db_driver = "org.h2.Driver";
//    private static final Service<RecipeImpl> recipeService = new RecipeServiceImpl();
//    private static Service<IngredientImpl> ingredientService = new IngredientServiceImpl();
//    private static UserRepository repository;
//    private static UserServiceImpl userService;

    @Test
    public void save() {
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
                .imagesList(null).cookingTime(50L).author("Author").publishDate(new GregorianCalendar(1998, 05, 23)).stepsDescription(null).difficulty("easy")
                .userList(userList).build();

        UserImpl user = UserImpl.builder().id(1L)
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