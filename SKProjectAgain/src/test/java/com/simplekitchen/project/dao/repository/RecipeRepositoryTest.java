package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class RecipeRepositoryTest {

    RecipeRepository repository = Mockito.mock(RecipeRepository.class);


    @Before
    public void setUp() throws Exception {
        Mockito.when(repository.findByName(Mockito.anyString()))
                .thenReturn(Optional.of(Collections.singletonList(RecipeEntityImpl.builder().build())));
        Mockito.when(repository.findByDifficulty(Mockito.anyString()))
                .thenReturn(Optional.of(Collections.singletonList(RecipeEntityImpl.builder().build())));
        Mockito.when(repository.findByCookingTime(Mockito.anyLong()))
                .thenReturn(Optional.of(Collections.singletonList(RecipeEntityImpl.builder().build())));
//        Mockito.when(repository.deleteAllByName(Mockito.anyString())).thenReturn();

    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(repository);
    }

    @Test
    public void findByNameSuccess() {
        List<RecipeEntity> name;

        name = repository.findByName("Name").orElse(Collections.emptyList());

        Assert.assertEquals(1, name.size());
    }

    @Test
    public void findByNameFail() {
//        Mockito.when(repository.findByName(Mockito.anyString())).thenThrow(new Exception("Ошибка работы репозитория"));
//
//        Assert.assertThrows(Exception.class,() -> repository.findByName("N"));
    }

    @Test
    public void findByDifficultySuccess() {
        List<RecipeEntity> dif;

        dif = repository.findByDifficulty("Dif").orElse(Collections.emptyList());

        Assert.assertEquals(1, dif.size());
    }

    @Test
    public void findByDifficultyFail() {
    }

    @Test
    public void findByCookingTimeSuccess() {
        List<RecipeEntity> list;

        list = repository.findByCookingTime(1L).orElse(Collections.emptyList());

        Assert.assertEquals(1, list.size());
    }

    @Test
    public void findByCookingTimeFail() {
    }

    @Test
    public void deleteAllByNameSuccess() {
        repository.deleteById(1L);
    }

    @Test
    public void deleteAllByNameFail() {
    }
}