package com.simplekitchen.project.business.mapper;

import com.simplekitchen.project.business.mapper.recipe.RecipeMapper;
import com.simplekitchen.project.dao.entity.image.ImageEntityImpl;
import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.StepDescriptionEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dto.entity.image.ImageImpl;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.StepDescriptionImpl;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class RecipeMapperTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mapRecipeEntityDaoToRecipeImplDtoSuccess() {
        RecipeEntityImpl daoRecipe = RecipeEntityImpl.builder()
                .name("Name")
                .difficulty("Difficulty")
                .ingredientsList(Collections.singletonList(IngredientEntityImpl.builder()
                        .name("IngredientName")
                        .build()))
                .cookingTime(30L)
                .userList(Collections.singletonList(UserEntityImpl.builder().name("UserName").build()))
                .stepDescriptionList(Collections.singletonList(StepDescriptionEntityImpl.builder()
                        .description("Description text")
                        .build()))
                .publishDate(new GregorianCalendar(2000, Calendar.FEBRUARY, 11))
                .imageList(Collections.singletonList(ImageEntityImpl.builder().path("Path").build()))
                .author("Author")
                .id(1L)
                .description("Recipe description")
                .build();
        RecipeImpl dtoRecipe;

        dtoRecipe = RecipeMapper.INSTANCE.map(daoRecipe);

        Assert.assertEquals(daoRecipe.getId(), dtoRecipe.getId());
        Assert.assertEquals(daoRecipe.getDescription(), dtoRecipe.getDescription());
        Assert.assertEquals(daoRecipe.getAuthor(), dtoRecipe.getAuthor());
        Assert.assertEquals(daoRecipe.getPublishDate(), dtoRecipe.getPublishDate());
        Assert.assertEquals(daoRecipe.getCookingTime(), dtoRecipe.getCookingTime());
        Assert.assertEquals(daoRecipe.getDifficulty(), dtoRecipe.getDifficulty());
        Assert.assertEquals(daoRecipe.getName(), dtoRecipe.getName());
        Assert.assertEquals(
                daoRecipe.getImageList().get(0).getPath(),
                dtoRecipe.getImageList().get(0).getPath()
        );
        Assert.assertEquals(
                daoRecipe.getStepDescriptionList().get(0).getDescription(),
                dtoRecipe.getStepDescriptionList().get(0).getDescription()
        );
        Assert.assertEquals(
                daoRecipe.getIngredientsList().get(0).getName(),
                dtoRecipe.getIngredientsList().get(0).getName()
        );
    }

    @Test
    public void mapRecipeImplDtoToRecipeEntityDaoSuccess() {
        RecipeEntityImpl daoRecipe;
        RecipeImpl dtoRecipe = RecipeImpl.builder()
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
                .build();

        daoRecipe = RecipeMapper.INSTANCE.map(dtoRecipe);

        Assert.assertEquals(dtoRecipe.getId(), daoRecipe.getId());
        Assert.assertEquals(dtoRecipe.getDescription(), daoRecipe.getDescription());
        Assert.assertEquals(dtoRecipe.getAuthor(), daoRecipe.getAuthor());
        Assert.assertEquals(dtoRecipe.getPublishDate(), daoRecipe.getPublishDate());
        Assert.assertEquals(dtoRecipe.getCookingTime(), daoRecipe.getCookingTime());
        Assert.assertEquals(dtoRecipe.getDifficulty(), daoRecipe.getDifficulty());
        Assert.assertEquals(dtoRecipe.getName(), daoRecipe.getName());
        Assert.assertEquals(
                dtoRecipe.getImageList().get(0).getPath(),
                daoRecipe.getImageList().get(0).getPath()
        );
        Assert.assertEquals(
                dtoRecipe.getStepDescriptionList().get(0).getDescription(),
                daoRecipe.getStepDescriptionList().get(0).getDescription()
        );
        Assert.assertEquals(
                dtoRecipe.getIngredientsList().get(0).getName(),
                daoRecipe.getIngredientsList().get(0).getName()
        );
    }

    @Test
    public void mapRecipeListToRecipeListImplSuccess() {
        try {
            RecipeList interfaceRecipeList = new RecipeListImpl();
            RecipeListImpl classRecipeList;

            interfaceRecipeList.getRecipeList().add(RecipeImpl.builder().name("RecipeName").build());
            classRecipeList = RecipeMapper.INSTANCE.map(interfaceRecipeList);

            Assert.assertEquals(interfaceRecipeList.getRecipeList(), classRecipeList.getRecipeList());
        } catch (Throwable e) {
            Assert.fail();
        }
    }

}