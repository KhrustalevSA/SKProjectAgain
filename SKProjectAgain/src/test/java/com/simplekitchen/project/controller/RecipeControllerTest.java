package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.business.service.RecipeControllerServiceImpl;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.business.utils.RecipeInfoRequestValidator;
import com.simplekitchen.project.business.utils.RecipeSaveValidator;
import com.simplekitchen.project.business.utils.api.ObjectSaveValidator;
import com.simplekitchen.project.business.utils.api.RequestValidator;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.common.api.LongList;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeResponseInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeListRequestInfo;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import org.assertj.core.util.Lists;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RecipeControllerTest {

    private final RecipeService service = Mockito.mock(RecipeService.class);

    private final RecipeControllerService controllerService = new RecipeControllerServiceImpl(
            service,
            new RecipeInfoRequestValidator(),
            new RecipeSaveValidator()
    );
    private final RecipeController controller = new RecipeController(controllerService);

    @Before
    public void setUp() throws Throwable {
        Mockito.when(service.save(Mockito.any())).thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                        .name("Name").difficulty("Easy")
                .build()));
        Mockito.when(service.findAll()).thenReturn(Collections.singletonList(RecipeEntityImpl.builder().build()));
        Mockito.when(service.deleteById(Mockito.anyLong())).thenReturn(true);
    }

    @After
    public void tearDown() throws Exception {
        Mockito.reset(service);
    }

    @Test
    public void saveSuccess() {
        RecipeListRequestInfoImpl request = RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .difficulty("Easy")
                        .build()))
                .build();
        RecipeResponseInfo savedResponse = null;
        try {
             savedResponse = controller.save(request);
        } catch (BaseException e) {
            Assert.fail();
        }

        Assert.assertEquals(request.getRecipeList(), savedResponse.getRecipeList());
    }

    @Test
    public void saveFail() {
        try {
            Mockito.when(service.save(Mockito.any())).thenThrow(new DataBaseException(""));
            assertFalse(controller.save(
                    RecipeListRequestInfoImpl.builder()
                    .recipeList(Collections.singletonList(RecipeImpl.builder()
                            .name("Name")
                            .build()))
                    .build()).getStatus().isSuccess());
            assertFalse(controller.save(RecipeListRequestInfoImpl.builder()
                    .recipeList(Collections.singletonList(RecipeImpl.builder()
                            .difficulty("Difficulty")
                            .build()))
                    .build()).getStatus().isSuccess());
            assertFalse(controller.save(RecipeListRequestInfoImpl.builder()
                    .recipeList(Collections.singletonList(RecipeImpl.builder()
                            .cookingTime(1L)
                            .build()))
                    .build()
            ).getStatus().isSuccess());
            assertFalse(controller.save(RecipeListRequestInfoImpl.builder()
                    .recipeList(Collections.singletonList(RecipeImpl.builder()
                            .name("Name")
                            .difficulty("Difficulty")
                            .build()))
                    .build()
            ).getStatus().isSuccess());
        } catch (Throwable e) {
            Assert.fail();
        }
    }

    @Test
    public void getByNameSuccess() {
        RecipeRequestInfoImpl requestInfo = RecipeRequestInfoImpl.builder().name("Name").build();
        RecipeResponseInfo responseInfo = null;
        try {
            Mockito.when(service.findByName(Mockito.any()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                                .name("Name")
                                .build()));
            responseInfo = controller.get(requestInfo);

        } catch (BaseException | DataBaseException e) {
            Assert.fail();
        }

        Assert.assertEquals(requestInfo.getName(), responseInfo.getRecipeList().get(0).getName());
    }

    @Test
    public void getByIdSuccess() {
        RecipeRequestInfoImpl requestInfo = RecipeRequestInfoImpl.builder().id(1L).build();
        RecipeResponseInfo responseInfo = null;
        try {
            Mockito.when(service.findById(Mockito.anyLong()))
                    .thenReturn(RecipeEntityImpl.builder()
                            .id(1L)
                            .build());
            responseInfo = controller.get(requestInfo);

        } catch (BaseException | DataBaseException e) {
            Assert.fail();
        }

        Assert.assertEquals(requestInfo.getId(), responseInfo.getRecipeList().get(0).getId());
    }

    @Test
    public void getFailButReturnGoodResponse() {
        try {
            Mockito.when(service.findByName(Mockito.anyString())).thenThrow(DataBaseException.class);
            Mockito.when(service.findById(Mockito.anyLong())).thenThrow(DataBaseException.class);
            Assert.assertEquals(
                    RecipeResponseInfoImpl.builder()
                            .status(StatusImpl.builder()
                                    .success(true)
                                    .description(null)
                                    .build())
                            .recipeList(Collections.emptyList())
                            .build(),
                    controller.get(RecipeRequestInfoImpl.builder().name("Name").build())
            );
            Assert.assertEquals(
                    RecipeResponseInfoImpl.builder()
                            .status(StatusImpl.builder()
                                    .success(true)
                                    .description(null)
                                    .build())
                            .recipeList(Collections.emptyList())
                            .build(),
                    controller.get(RecipeRequestInfoImpl.builder().id(1L).build())
            );
        } catch (Throwable e) {
            Assert.fail();
        }
    }

    @Test
    public void getValidationFail() {
        try {
            Assert.assertEquals(
                    RecipeResponseInfoImpl.builder()
                            .status(StatusImpl.builder()
                                    .success(false)
                                    .description("Не все обязательные поля заполнены")
                                    .build())
                            .build(),
                    controller.get(RecipeRequestInfoImpl.builder().build())
            );
        } catch (BaseException e) {
            Assert.fail();
        }
    }

    @Test
    public void getAllSuccess() {
        RecipeResponseInfo responseInfo;

        responseInfo = controller.getAll();

        Assert.assertEquals(
                RecipeResponseInfoImpl.builder()
                        .recipeList(Collections.singletonList(RecipeImpl.builder().build()))
                        .status(StatusImpl.builder().success(true).build())
                        .build(),
                responseInfo);
    }

    @Test
    public void getAllFail() {
        try {
            Mockito.when(service.findAll()).thenThrow(DataBaseException.class);
        } catch (Throwable e) {
            Assert.fail();
        }

        Assert.assertEquals(
                RecipeResponseInfoImpl.builder()
                        .recipeList(Collections.emptyList())
                        .status(StatusImpl.builder().success(true).build())
                        .build(),
                controller.getAll()
        );

    }

    @Test
    public void deleteByIdSuccess() {
        try {
            Assert.assertTrue(controller.deleteById(1L));
        } catch (BaseException e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteByIdFail() {
        try {
            Mockito.when(service.deleteById(Mockito.anyLong())).thenThrow(DataBaseException.class);
            Assert.assertEquals(false, controller.deleteById(1L));
        } catch (Throwable e) {
            Assert.fail();
        }
    }

    @Test
    public void deleteByIdListSuccess() {
        Assert.assertTrue(controller.deleteByIdList(LongListImpl.builder()
                .longList(Collections.singletonList(1L))
                .build()));
    }

    @Test
    public void deleteByIdListFail() {
        try {
            Mockito.when(service.deleteById(Mockito.anyLong())).thenThrow(DataBaseException.class);
        } catch (Throwable e) {
            Assert.fail();
        }
        Assert.assertEquals(
                false,
                controller.deleteByIdList(LongListImpl.builder().build())
        );
        Assert.assertEquals(
                false,
                controller.deleteByIdList(LongListImpl.builder().longList(Collections.emptyList()).build())
        );
    }

}