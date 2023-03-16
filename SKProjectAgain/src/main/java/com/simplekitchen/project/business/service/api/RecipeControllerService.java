package com.simplekitchen.project.business.service.api;

import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.dto.common.LongListImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;

import java.util.List;

/**
 * интерфейс сервиса контроллера рецептов
 * @author KhrustalevSA
 * @since 16.03.2023
 */
public interface RecipeControllerService {

    /**
     * метод сохранения рецепта
     * @param recipeListRequestInfo объект рецепта для сохранения
     * @return сохраненный рецепт
     * @throws BaseException общий класс ошибок приложения
     */
    RecipeResponseInfo save(RecipeListRequestInfoImpl recipeListRequestInfo) throws Throwable;

    /**
     * метод получения списка рецептов по запрошенной информации о них
     * @param recipeRequestInfo запрос с инофрмацией о рецептах
     * @return список найденных рецептов
     * @throws BaseException ощий клпсс ошибок
     */
    List<Recipe> get(RecipeRequestInfoImpl recipeRequestInfo) throws BaseException;

    /**
     * метод получения всех существующих рецептов
     * @return список всех рецептов
     */
    List<Recipe> getAll();

    /**
     * метод для получения списка рецептов по их уникальным идентификаторам
     * @param longList список идентификаторов
     * @return список найденных рецептов
     */
    List<Recipe> getAllById(LongListImpl longList);

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id уникаьлный идентификатор
     * @return логический результат удаления
     */
    Boolean deleteById(Long id) throws BaseException;

    /**
     * метод удаления рецепта  по переданной информации
     * @param recipeRequestInfo класс с информацией для удаления
     * @return логический результат удаления
     */
    Boolean deleteByName(RecipeRequestInfo recipeRequestInfo);




}
