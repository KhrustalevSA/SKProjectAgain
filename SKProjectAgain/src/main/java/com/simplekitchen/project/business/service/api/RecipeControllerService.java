package com.simplekitchen.project.business.service.api;

import com.simplekitchen.project.business.entity.common.api.LongList;
import com.simplekitchen.project.business.entity.recipe.api.RecipeRequestInfo;
import com.simplekitchen.project.business.exception.BaseException;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImplListImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;

/**
 * интерфейс сервиса контроллера рецептов
 */
public interface RecipeControllerService {

    /**
     * метод сохранения рецепта
     * @param recipe объект рецепта для сохранения
     * @return сохраненный рецепт
     * @throws DataBaseException ошибка базы данных
     * @throws BaseException общий класс ошибок приложения
     */
    Recipe save(RecipeImpl recipe) throws DataBaseException, BaseException;

    /**
     * метод сохранения всех рецептов, возвращает список сохраненных рецептов
     * @param recipeList список рецептов для сохранения
     * @return список сохраненных рецептов
     */
    RecipeList saveAll(RecipeImplListImpl recipeList) throws BaseException, DataBaseException;

    /**
     * метод получения рецепта по уникальному идентификатору
     * @param id уникальный идентификатор рецепта
     * @return найденный объект рецепта
     */
    Recipe get(Long id) throws DataBaseException, BaseException;

    /**
     * метод получения рецепта по классу информации
     * @param requestInfo класс с информацией для поиска рецепта
     * @return список найденных рецептов
     */
    RecipeList get(RecipeRequestInfo requestInfo) throws BaseException;

    /**
     * метод получения всех существующих рецептов
     * @return список всех рецептов
     */
    RecipeList getAll();

    /**
     * метод для получения списка рецептов по их уникальным идентификаторам
     * @param longList список идентификаторов
     * @return список найденных рецептов
     */
    RecipeList getAllById(LongList longList);

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
