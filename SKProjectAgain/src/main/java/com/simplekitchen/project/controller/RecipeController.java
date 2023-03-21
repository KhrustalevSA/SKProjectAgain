package com.simplekitchen.project.controller;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.business.service.RecipeControllerServiceImpl;
import com.simplekitchen.project.business.service.api.RecipeControllerService;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeResponseInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.Collections;

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
    RecipeController(RecipeControllerService recipeService) {
        recipeControllerService = recipeService;
    }

    /**
     * метод сохранения рецепта
     * @param recipeListRequestInfo объект рецепта для сохранения
     * @return сохраненный рецепт
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @PostMapping("/save")
    public RecipeResponseInfo save(@RequestBody RecipeListRequestInfoImpl recipeListRequestInfo) throws BaseException {
        try {
            return recipeControllerService.save(recipeListRequestInfo);
        } catch (Throwable e) {
            log.error(String.format("Ошибка сохранения рецепта %s", recipeListRequestInfo));
            log.error(e.getMessage(), e.getCause());
            return RecipeResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }
    }

    /**
     * меттод получения рецептов по переданнной информации
     * @param recipeRequestInfo переданная информация о рецепте
     * @return информация об успешности операции найденных рецептах
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @GetMapping("/get")
    public RecipeResponseInfo get(@RequestBody RecipeRequestInfoImpl recipeRequestInfo) throws BaseException {
        try {
            return RecipeResponseInfoImpl.builder()
                    .recipeList(recipeControllerService.get(recipeRequestInfo))
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        } catch (Throwable e) {
            log.error(String.format("Ошибка получения рецепта %s", recipeRequestInfo));
            log.error(e.getMessage(), e.getCause());
            return RecipeResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }
    }

    /**
     * метод получения всех имеющихся рецептов
     * @return информация об успешности операции найденных рецептах
     */
    @GetMapping("/getAll")
    public RecipeResponseInfo getAll() {
        try {
            return RecipeResponseInfoImpl.builder()
                    .recipeList(recipeControllerService.getAll())
                    .status(StatusImpl.builder().success(true).build())
                    .build();
        } catch (Throwable e) {
            log.error("Ошибка получения всех рецептов.");
            log.error(e.getMessage(), e.getCause());
            return RecipeResponseInfoImpl.builder()
                    .status(StatusImpl.builder().success(false).description(e.getMessage()).build())
                    .build();
        }
    }

    /**
     * метод удаления рецепта по уникальному идентификатору
     * @param id идентификатор рецепта
     * @return логический ответ
     * @throws BaseException общий класс ошибки обработки исключения приложения
     */
    @PostMapping("/deleteById")
    public Boolean deleteById(@RequestParam Long id) throws BaseException {
        try {
            return recipeControllerService.deleteById(id);
        } catch (Throwable e) {
            log.error(String.format("Ошибка удаления рецепта по идентификатору %s", id));
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }

    /**
     * метод удаления рецептов по списку идентификаторов
     * @param longList список уникальных идентификаторов
     * @return логический ответ
     */
    @PostMapping("/deleteByIdList")
    public Boolean deleteByIdList(@RequestBody LongListImpl longList) {
        try {
            if (longList == null || longList.getLongList().isEmpty()) {
                throw new ValidationException("Передан пустой список целых чисел.");
            }
            boolean deleteCheck = true;
            for (Long id : longList.getLongList()) {
                deleteCheck = deleteCheck && recipeControllerService.deleteById(id);
            }
            return deleteCheck;
        } catch (Throwable e) {
            log.error(String.format("Ошибка удаления рецептов по списку идентификаторов %s", longList));
            log.error(e.getMessage(), e.getCause());
            return false;
        }
    }



    @PostMapping("/show")
    public RecipeListRequestInfoImpl show() {
        return RecipeListRequestInfoImpl.builder()
                .recipeList(Collections.singletonList(RecipeImpl.builder()
                        .name("Name")
                        .difficulty("Easy")
                        .ingredientsList(Collections.singletonList(IngredientImpl.builder()
                                .name("Egg")
                                .build()))
                        .build()))
                .build();
    }
}
