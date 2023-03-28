package com.simplekitchen.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.RecipeControllerServiceImpl;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.business.utils.RecipeInfoRequestValidator;
import com.simplekitchen.project.business.utils.RecipeSaveValidator;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.api.RecipeService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeResponseInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

import static org.junit.Assert.assertFalse;

public class RecipeControllerTest {

    private final RecipeService service = Mockito.mock(RecipeService.class);

    private final RecipeControllerService controllerService = new RecipeControllerServiceImpl(
            service,
            new RecipeInfoRequestValidator(),
            new RecipeSaveValidator()
    );
    private final RecipeController controller = new RecipeController(controllerService);

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final File jsonFileWithRecipeRequest = new File("src/test/resources/static/Json/recipe/RecipeListRequestInfo.json");
    private final File jsonFileWithRecipeListRequestInfoOnlyWithCookingTime =
            new File("src/test/resources/static/Json/recipe/RecipeListRequestInfoOnlyWithCookingTime.json");
    private final  File jsonFileWithRecipeListRequestInfoOnlyWithDifficulty =
            new File("src/test/resources/static/Json/recipe/RecipeListRequestInfoOnlyWithDifficulty.json");
    private final File jsonFileWithRecipeListRequestInfoOnlyWithName =
            new File("src/test/resources/static/Json/recipe/RecipeListRequestInfoOnlyWithName.json");
    private final File jsonFileWithRecipeRequestInfoOnlyWithName =
            new File("src/test/resources/static/Json/recipe/RecipeRequestInfoOnlyWithName.json");
    private final File jsonFileWithRecipeRequestInfoOnlyWithId =
            new File("src/test/resources/static/Json/recipe/RecipeRequestInfoOnlyWithId.json");
    private final File jsonFileWithRecipeRequestEmpty =
            new File("src/test/resources/static/Json/recipe/RecipeRequestInfoEmpty.json");
    private final File jsonFileWithLongList =
            new File("src/test/resources/static/Json/common/LongList.json");
    private final File jsonFileWithLongListWithEmptyList =
            new File("src/test/resources/static/Json/common/LongListWithEmptyList.json");
    private final File jsonFileWithLongListEmpty =
            new File("src/test/resources/static/Json/common/LongListEmpty.json");


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
        RecipeListRequestInfoImpl request = null;
        RecipeResponseInfo response = null;
        try {
            request = objectMapper.readValue(jsonFileWithRecipeRequest, RecipeListRequestInfoImpl.class);
            response = controller.save(request);
        } catch (Throwable e) {
            Assert.fail();
        }

        Assert.assertEquals(request.getRecipeList(), response.getRecipeList());
    }

    @Test
    public void saveFail() {
        RecipeListRequestInfoImpl requestInfo;

        try {
            Mockito.when(service.save(Mockito.any())).thenThrow(new DataBaseException(""));

            requestInfo = objectMapper.readValue(
                    jsonFileWithRecipeListRequestInfoOnlyWithName,
                    RecipeListRequestInfoImpl.class
            );
            assertFalse(controller.save(requestInfo).getStatus().isSuccess());

            requestInfo = objectMapper.readValue(
                    jsonFileWithRecipeListRequestInfoOnlyWithDifficulty,
                    RecipeListRequestInfoImpl.class
            );
            assertFalse(controller.save(requestInfo).getStatus().isSuccess());

            requestInfo = objectMapper.readValue(
                    jsonFileWithRecipeListRequestInfoOnlyWithCookingTime,
                    RecipeListRequestInfoImpl.class
            );
            assertFalse(controller.save(requestInfo).getStatus().isSuccess());

            requestInfo = objectMapper.readValue(
                    jsonFileWithRecipeRequest,
                    RecipeListRequestInfoImpl.class
            );
            assertFalse(controller.save(requestInfo).getStatus().isSuccess());

        } catch (Throwable e) {
            Assert.fail();
        }
    }

    @Test
    public void getByNameSuccess() {
        RecipeRequestInfoImpl requestInfo = null;
        RecipeResponseInfo responseInfo = null;
        try {
            Mockito.when(service.findByName(Mockito.any()))
                .thenReturn(Collections.singletonList(RecipeEntityImpl.builder()
                                .name("Name")
                                .build()));

            requestInfo = objectMapper.readValue(jsonFileWithRecipeRequestInfoOnlyWithName, RecipeRequestInfoImpl.class);
            responseInfo = controller.get(requestInfo);

        } catch (Throwable e) {
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
                    controller.get(objectMapper.readValue(
                            jsonFileWithRecipeRequestInfoOnlyWithName,
                            RecipeRequestInfoImpl.class)
                    )
            );
            Assert.assertEquals(
                    RecipeResponseInfoImpl.builder()
                            .status(StatusImpl.builder()
                                    .success(true)
                                    .description(null)
                                    .build())
                            .recipeList(Collections.emptyList())
                            .build(),
                    controller.get(objectMapper.readValue(
                            jsonFileWithRecipeRequestInfoOnlyWithId,
                            RecipeRequestInfoImpl.class)
                    )
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
                    controller.get(objectMapper.readValue(jsonFileWithRecipeRequestEmpty, RecipeRequestInfoImpl.class))
            );
        } catch (Throwable e) {
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
        LongListImpl longList = null;
        try {
            longList = objectMapper.readValue(jsonFileWithLongList, LongListImpl.class);
        } catch (IOException e) {
            Assert.fail();
        }
        Assert.assertTrue(controller.deleteByIdList(longList));
    }

    @Test
    public void deleteByIdListFail() {
        LongListImpl longListEmpty = null;
        LongListImpl longListWithEmptyList = null;
        try {
            longListEmpty = objectMapper.readValue(jsonFileWithLongListEmpty, LongListImpl.class);
            longListWithEmptyList = objectMapper.readValue(jsonFileWithLongListWithEmptyList, LongListImpl.class);
            Mockito.when(service.deleteById(Mockito.anyLong())).thenThrow(DataBaseException.class);
        } catch (Throwable e) {
            Assert.fail();
        }
        Assert.assertEquals(
                false,
                controller.deleteByIdList(longListEmpty)
        );
        Assert.assertEquals(
                false,
                controller.deleteByIdList(longListWithEmptyList)
        );
    }
}