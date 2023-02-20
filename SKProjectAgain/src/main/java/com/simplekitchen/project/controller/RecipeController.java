package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.entity.common.LongListImpl;
import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.business.entity.recipe.RecipeResponseInfoImpl;
import com.simplekitchen.project.business.entity.recipe.api.RecipeResponseInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.RecipeControllerServiceImpl;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * РЕСТ контроллер для работы с рецептами
 */
@Slf4j
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private static final RecipeResponseInfo INVALID_DATA = RecipeResponseInfoImpl.builder()
            .status(StatusImpl.builder().success(false).description("Некорректно введенные данные").build())
            .build();

    private final RecipeControllerService recipeControllerService;

    @Autowired
    RecipeController(RecipeControllerServiceImpl recipeService) {
        recipeControllerService = recipeService;
    }

    @PostMapping("/save")
    public RecipeResponseInfo save(@RequestBody RecipeImpl recipe) throws BaseException, DataBaseException {
        if (validate(recipe)) {
            Recipe savedRecipe = recipeControllerService.save(recipe);
            if (savedRecipe.getId() == null) {
                return RecipeResponseInfoImpl.builder()
                        .status(StatusImpl.builder()
                                .success(false)
                                .description("Ошибка сохранения рецепта")
                                .build())
                        .build();
            }
            return RecipeResponseInfoImpl.builder()
                    .recipeList(Lists.newArrayList(savedRecipe))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        }
        return INVALID_DATA;
    }

    @PostMapping("/save/all")
    public RecipeResponseInfo saveAll(@RequestBody RecipeListImpl recipeList) throws BaseException, DataBaseException {
        if (validate(recipeList)) {
            RecipeList savedRecipe = recipeControllerService.saveAll(recipeList);
            if (recipeList.getRecipeList() != null) {
                return RecipeResponseInfoImpl.builder()
                        .recipeList(savedRecipe.getRecipeList())
                        .status(StatusImpl.builder().success(true).build())
                        .build();
            }
        }
        return INVALID_DATA;
    }

    @GetMapping("/get")
    public RecipeResponseInfo get(@RequestBody RecipeRequestInfoImpl recipeRequestInfo) throws BaseException {
        if (validate(recipeRequestInfo)) {
            RecipeList recipeList = recipeControllerService.get(recipeRequestInfo);
            if (recipeList.getRecipeList() != null) {
                return RecipeResponseInfoImpl.builder()
                        .recipeList(recipeList.getRecipeList())
                        .status(StatusImpl.builder().success(true).build())
                        .build();
            }
        }
        return INVALID_DATA;
    }

    @GetMapping("/get/all")
    public RecipeResponseInfo getAll() {
        RecipeList recipeList = recipeControllerService.getAll();
        if (recipeList.getRecipeList() != null) {
            return RecipeResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(true).build())
                    .recipeList(recipeList.getRecipeList())
                    .build();
        }
        return INVALID_DATA;
    }

    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) throws BaseException {
        if (validate(id)){
            return recipeControllerService.deleteById(id);
        }
        return false;
    }

    @PostMapping("/deleteByIdList")
    public Boolean deleteByIdList(@RequestBody LongListImpl longList) throws BaseException {
        if (validate(longList)) {
            Boolean deleteCheck;
            for (Long id : longList.getLongList()) {
                deleteCheck = recipeControllerService.deleteById(id);
                if (!deleteCheck) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @PostMapping("/showRecipeEntity")
    public RecipeImpl showUserEntity(){
        return RecipeImpl.builder().id(1L).name("Pasta").build();
    }

    @PostMapping("/showRecipeListEntity")
    public RecipeList showUserListEntity(){
        return RecipeListImpl.builder()
                .recipeList(Lists.newArrayList(
                        RecipeImpl.builder().id(1L).name("Pasta").build(),
                        RecipeImpl.builder().id(2L).name("Eggs").build()))
                .build();
    }

    private Boolean validate(Object o){
        return o != null;
    }
}
