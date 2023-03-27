package com.simplekitchen.project.dao.service;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.RecipeService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RecipeServiceImplTest {

    private final RecipeService recipeService = Mockito.mock(RecipeService.class);

    @Before
    public void setUp() throws Throwable {
        Mockito.when(recipeService.save(Mockito.any()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                        .name("Name")
                        .build()));
        Mockito.when(recipeService.findById(Mockito.anyLong()))
                .thenReturn(RecipeEntityImpl.builder()
                        .name("Name")
                        .build());
        Mockito.when(recipeService.findByName(Mockito.anyString()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                        .name("Name")
                        .build()));
        Mockito.when(recipeService.findByDifficulty(Mockito.anyString()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                        .name("Name")
                        .build()));
        Mockito.when(recipeService.findByCookingTime(Mockito.anyLong()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                        .name("Name")
                        .build()));
        Mockito.when(recipeService.findAll())
                        .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                                .name("Name")
                                .build()));
        Mockito.when(recipeService.findAllById(Mockito.any()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder().name("Name").build()));
        Mockito.when(recipeService.deleteById(Mockito.anyLong()))
                .thenReturn(Boolean.TRUE);
        Mockito.when(recipeService.deleteByName(Mockito.anyString()))
                .thenReturn(Boolean.TRUE);
        Mockito.when(recipeService.deleteAllById(Mockito.any()))
                .thenReturn(Boolean.TRUE);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(recipeService);
    }

    @Test
    public void saveSuccess() {
        List<RecipeEntity> recipeEntityList = null;
        try {
            recipeEntityList = recipeService.save(Collections.singletonList(RecipeEntityImpl.builder().name("Name").build()));
        } catch (DataBaseException e) {
            Assert.fail();
        }

        Assert.assertEquals(1, recipeEntityList.size());
    }

    @Test
    public void saveFail() {
        try {
            Mockito.when(recipeService.save(Mockito.any())).thenThrow(DataBaseException.class);
        } catch (Throwable e) {
            Assert.fail();
        }

    }

    @Test
    public void findByIdSuccess() {
    }

    @Test
    public void findByIdFail() {
    }

    @Test
    public void findByNameSuccess() {
    }

    @Test
    public void findByNameFail() {
    }

    @Test
    public void findByDifficultySuccess() {
    }

    @Test
    public void findByDifficultyFail() {
    }

    @Test
    public void findByCookingTimeSuccess() {
    }

    @Test
    public void findByCookingTimeFail() {
    }

    @Test
    public void findAllSuccess() {
    }

    @Test
    public void findAllFail() {
    }

    @Test
    public void findAllByIdSuccess() {
    }

    @Test
    public void findAllByIdFail() {
    }

    @Test
    public void deleteByIdSuccess() {
    }

    @Test
    public void deleteByIdFail() {
    }

    @Test
    public void deleteByNameSuccess() {
    }

    @Test
    public void deleteByNameFail() {
    }

    @Test
    public void deleteAllByIdSuccess() {
    }

    @Test
    public void deleteAllByIdFail() {
    }

}