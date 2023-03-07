package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.RecipeControllerServiceImpl;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeResponseInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * РЕСТ контроллер для работы с рецептами
 * @author KhrustalevSA
 * @since 26.02.2023
 */
@Slf4j
@RestController
@RequestMapping("/recipe")
public class RecipeController {

    /**
     * поле некорректного веб ответа
     */
    private static final RecipeResponseInfo INVALID_DATA = RecipeResponseInfoImpl.builder()
            .status(StatusImpl.builder().success(false).description("Некорректно введенные данные").build())
            .build();

    /**
     * сервис рецептов
     */
    private final RecipeControllerService recipeControllerService;

    /**
     * контроллер с автоматическим определением сервиса
     * @param recipeService сервис с методами обработки рецептов
     */
    @Autowired
    RecipeController(RecipeControllerServiceImpl recipeService) {
        recipeControllerService = recipeService;
    }

    /**
     * метод сохранения рецепта
     * @param recipe объект рецепта для сохранения
     * @return сохраненный рецепт
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @PostMapping("/save")
    public RecipeResponseInfo save(@RequestBody RecipeImpl recipe) throws BaseException {
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

    /**
     * метод сохранения списка рецептов
     * @param recipeList список сохраняемых рецептов
     * @return информация об успешности операции найденных рецептах
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @PostMapping("/save/all")
    public RecipeResponseInfo saveAll(@RequestBody List<RecipeImpl> recipeList) throws BaseException {
        if (validate(recipeList)) {
            List<Recipe> savedRecipe = recipeControllerService.saveAll(recipeList);
            if (recipeList != null) {
                return RecipeResponseInfoImpl.builder()
                        .recipeList(savedRecipe)
                        .status(StatusImpl.builder().success(true).build())
                        .build();
            }
        }
        return INVALID_DATA;
    }

    /**
     * меттод получения рецептов по переданнной информации
     * @param recipeRequestInfo переданная информация о рецепте
     * @return информация об успешности операции найденных рецептах
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @GetMapping("/get")
    public RecipeResponseInfo get(@RequestBody RecipeRequestInfoImpl recipeRequestInfo) throws BaseException {
        if (validate(recipeRequestInfo)) {
            List<Recipe> recipeList = recipeControllerService.get(recipeRequestInfo);
            if (recipeList != null) {
                return RecipeResponseInfoImpl.builder()
                        .recipeList(recipeList)
                        .status(StatusImpl.builder().success(true).build())
                        .build();
            }
        }
        return INVALID_DATA;
    }

    /**
     * метод получения всех имеющихся рецептов
     * @return информация об успешности операции найденных рецептах
     */
    @GetMapping("/get/all")
    public RecipeResponseInfo getAll() {
        List<Recipe> recipeList = recipeControllerService.getAll();
        if (recipeList != null) {
            return RecipeResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(true).build())
                    .recipeList(recipeList)
                    .build();
        }
        return INVALID_DATA;
    }

    /**
     * метод удаления рецепта по уникальному идентификатору
     * @param id идентификатор рецепта
     * @return логический ответ
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) throws BaseException {
        if (validate(id)){
            return recipeControllerService.deleteById(id);
        }
        return false;
    }

    /**
     * метод удаления рецептов по списку идентификаторов
     * @param longList список уникальных идентификаторов
     * @return логический ответ
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
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

    /**
     * метод проверки объекта на null
     * @param o переданный объект для валидации
     * @return логичский ответ
     */
    private Boolean validate(Object o){
        return o != null;
    }
}
