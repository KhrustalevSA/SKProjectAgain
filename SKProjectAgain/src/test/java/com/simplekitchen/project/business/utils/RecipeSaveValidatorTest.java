package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.dto.entity.image.ImageImpl;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.StepDescriptionImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

public class RecipeSaveValidatorTest {

    public RecipeSaveValidator validator = new RecipeSaveValidator();

    @Test
    public void validateForAllSuccess() {
        RecipeListRequestInfoImpl requestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .difficulty("Difficulty")
                        .ingredientsList(Collections.singletonList(IngredientImpl.builder()
                                .name("IngredientName")
                                .build()))
                        .cookingTime(30L)
                        .userList(Collections.singletonList(UserImpl.builder().name("UserName").build()))
                        .stepDescriptionList(Collections.singletonList(StepDescriptionImpl.builder()
                                .description("Description text")
                                .build()))
                        .publishDate(new GregorianCalendar(2000, Calendar.FEBRUARY, 11))
                        .imageList(Collections.singletonList(ImageImpl.builder().path("Path").build()))
                        .author("Author")
                        .id(1L)
                        .description("Recipe description")
                        .build()))
                .build();

        try {
            validator.validate(requestInfo);
            Assert.assertTrue(true);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void validateForNameAndDifficultySuccess() {
        validator = new RecipeSaveValidator();
        RecipeListRequestInfoImpl requestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .difficulty("Difficulty")
                        .build()))
                .build();

        try {
            validator.validate(requestInfo);
            Assert.assertTrue(true);
        } catch (ValidationException e) {
            Assert.fail();
        }
    }

    @Test
    public void validateWithoutNameFail() {
        validator = new RecipeSaveValidator();
        RecipeListRequestInfoImpl requestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .difficulty("Difficulty")
                        .build()))
                .build();

        Assert.assertThrows(ValidationException.class, () -> validator.validate(requestInfo));
    }

    @Test
    public void validateWithoutDifficultyFail() {
        validator = new RecipeSaveValidator();
        RecipeListRequestInfoImpl requestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .build()))
                .build();

        Assert.assertThrows(ValidationException.class, () -> validator.validate(requestInfo));
    }

    @Test
    public void validateWithoutAnyFail() {
        validator = new RecipeSaveValidator();
        RecipeListRequestInfoImpl requestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .build()))
                .build();

        Assert.assertThrows(ValidationException.class, () -> validator.validate(requestInfo));
    }
}