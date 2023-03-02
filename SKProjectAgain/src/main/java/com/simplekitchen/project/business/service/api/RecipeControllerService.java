package com.simplekitchen.project.business.service.api;

import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.recipe.RecipeRequestInfoImpl;
import com.simplekitchen.project.business.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;

import java.util.List;

/**
 * интерфейс сервиса контроллера рецептов
 */
public interface RecipeControllerService {

    /**
     * метод сохранения рецепта
     * @param recipe объект рецепта для сохранения
     * @return сохраненный рецепт
     * @throws BaseException общий класс ошибок приложения
     */
    Recipe save(RecipeImpl recipe) throws BaseException;

    /**
     * метод сохранения всех рецептов, возвращает список сохраненных рецептов
     * @param recipeList список рецептов для сохранения
     * @return список сохраненных рецептов
     */
    List<Recipe> saveAll(List<RecipeImpl> recipeList) throws BaseException;

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
    List<Recipe> getAllById(LongList longList);

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
