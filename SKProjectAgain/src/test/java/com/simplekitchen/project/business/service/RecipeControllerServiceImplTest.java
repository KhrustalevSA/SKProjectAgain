package com.simplekitchen.project.business.service;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.business.utils.RecipeInfoRequestValidator;
import com.simplekitchen.project.business.utils.RecipeSaveValidator;
import com.simplekitchen.project.dao.entity.common.entity.LongListImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class RecipeControllerServiceImplTest {

    private final RecipeService serviceDao = Mockito.mock(RecipeService.class);

    private final RecipeControllerService recipeControllerService = new RecipeControllerServiceImpl(
            serviceDao,
            new RecipeInfoRequestValidator(),
            new RecipeSaveValidator()
    );

    @Before
    public void setUp() throws DataBaseException {
        Mockito.when(serviceDao.save(Mockito.any()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder().build()));
        Mockito.when(serviceDao.findById(Mockito.anyLong())).thenReturn(RecipeEntityImpl.builder().build());
        Mockito.when(serviceDao.findByName(Mockito.anyString()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder().build()));
        Mockito.when(serviceDao.findByDifficulty(Mockito.anyString()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder().build()));
        Mockito.when(serviceDao.findByCookingTime(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder().build()));
        Mockito.when(serviceDao.findAll()).thenReturn(Collections.singletonList(RecipeEntityImpl.builder().build()));
        Mockito.when(serviceDao.deleteById(Mockito.anyLong())).thenReturn(Boolean.TRUE);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(serviceDao);
    }

    @Test
    public void saveRecipeSuccess() {
        RecipeListRequestInfoImpl recipeListRequestInfo = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .difficulty("Easy")
                        .build()))
                .build();
        RecipeResponseInfo savedRecipe = null;

        try {
            savedRecipe = recipeControllerService.save(recipeListRequestInfo);
        } catch (Throwable e) {
            Assert.fail();
        }

        Assert.assertEquals(1 ,savedRecipe.getRecipeList().size());
    }

    @Test
    public void saveRecipeFail() {
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.save(RecipeListRequestInfoImpl.builder().build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.save(RecipeListRequestInfoImpl.builder().recipeList(null).build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.save(RecipeListRequestInfoImpl.builder()
                                .recipeList(Collections.singletonList(RecipeImpl.builder()
                                                .name("Name")
                                                .build()))
                                .build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.save(RecipeListRequestInfoImpl.builder()
                        .recipeList(Collections.singletonList(RecipeImpl.builder()
                                .difficulty("Dif")
                                .build()))
                        .build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.save(RecipeListRequestInfoImpl.builder()
                        .recipeList(Collections.singletonList(RecipeImpl.builder()
                                .difficulty("Dif").ingredientsList(Collections.singletonList(IngredientImpl.builder().
                                        build()))
                                .build()))
                        .build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.save(RecipeListRequestInfoImpl.builder()
                        .recipeList(Collections.singletonList(RecipeImpl.builder()
                                .difficulty("Dif").ingredientsList(Collections.singletonList(IngredientImpl.builder().
                                        build()))
                                .cookingTime(26L)
                                .build()))
                        .build())
        );
    }

    @Test
    public void getRecipeSuccess() {
        RecipeRequestInfoImpl recipeRequestInfo = RecipeRequestInfoImpl.builder().name("Name").build();
        List<Recipe> recipeList = null;
        try {
            recipeList = recipeControllerService.get(recipeRequestInfo);
        } catch (Throwable e) {
            Assert.fail();
        }

        Assert.assertEquals(1, recipeList.size());
    }

    @Test
    public void getRecipeFail() {
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.get(RecipeRequestInfoImpl.builder().build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.get(RecipeRequestInfoImpl.builder().name(null).id(null).build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.get(RecipeRequestInfoImpl.builder().id(null).build())
        );
        Assert.assertThrows(
                ValidationException.class,
                () -> recipeControllerService.get(RecipeRequestInfoImpl.builder().name(null).build())
        );
    }

    @Test
    public void getAllSuccess() {
        List<Recipe> recipeList;

        recipeList = recipeControllerService.getAll();

        Assert.assertNotNull(recipeList);
    }

    @Test
    public void getAllByIdSuccess() {
        List<Recipe> recipeList = null;
        try {
            Mockito.when(serviceDao.findAllById(LongListImpl.builder().longList(Mockito.any()).build()))
                    .thenReturn(Lists.newArrayList(RecipeEntityImpl.builder().name("1").build(),
                            RecipeEntityImpl.builder().name("2").build(),
                            RecipeEntityImpl.builder().name("3").build()));
            recipeList = recipeControllerService.getAllById(com.simplekitchen.project.dto.common.LongListImpl.builder()
                    .longList(Lists.newArrayList(1L, 2L, 3L))
                    .build());
        } catch (Throwable e) {
            Assert.fail();
        }
        Assert.assertEquals(3, recipeList.size());
    }

    @Test
    public void deleteByIdSuccess() {
        Long id = 1L;
        Boolean deleteCheck = false;
        try {
            deleteCheck = recipeControllerService.deleteById(id);
        } catch (Throwable e) {
            Assert.fail();
        }
        Assert.assertEquals(true, deleteCheck);
    }

}