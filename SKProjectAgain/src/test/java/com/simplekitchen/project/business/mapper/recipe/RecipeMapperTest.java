package com.simplekitchen.project.business.mapper.recipe;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * класс тестов маппера рецептов
 * @author KhrustalevSA
 * @since 16.03.2023
 */
public class RecipeMapperTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void mapRecipeListInfoGetRecipeListImplToRecipeEntityList() {
        RecipeListRequestInfoImpl recipeListRequestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .difficulty("Easy")
                        .ingredientsList(Collections.singletonList(IngredientImpl.builder()
                                .name("Egg")
                                .build()))
                        .build()))
                .build();
        List<RecipeEntityImpl> recipeEntityList = null;
        List<RecipeImpl> recipeList = null;


        try {
            recipeEntityList = recipeListRequestInfo
                    .getRecipeList().stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList());

            recipeList = recipeListRequestInfo.getRecipeList();

        } catch (Throwable e) {
            Assert.fail();
        }

        Assert.assertEquals(
                recipeList,
                recipeEntityList.stream().map(RecipeMapper.INSTANCE::map).collect(Collectors.toList())
        );

    }
}