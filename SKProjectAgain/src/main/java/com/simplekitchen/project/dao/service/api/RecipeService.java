package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.common.LongListImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.exception.DataBaseException;

import java.util.List;

/**
 * интерфейс сервиса рецептов
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface RecipeService {

    /**
     * метод сохранения рецепта
     * @param recipeEntity сущность рецепта
     * @return сохраненный Optional объект рецепта
     */
    List<RecipeEntity> save(List<RecipeEntityImpl> recipeEntity) throws DataBaseException ;

    /**
     * метод получения рецепта по уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Optional объект полученного рецепта
     */
    RecipeEntity findById(Long id) throws DataBaseException;

    /**
     * метод поиска рецепта по названию
     * @param name название рецепта
     * @return список найденных рецептов
     * @throws DataBaseException ошибка базы данных
     */
    List<RecipeEntity> findByName(String name) throws DataBaseException ;

    /**
     * метод поиска рецепта по сложности
     * @param difficulty сложность рецепта
     * @return список найденных рецептов
     * @throws DataBaseException ошибка базы данных
     */
    List<RecipeEntity> findByDifficulty(String difficulty) throws DataBaseException;

    /**
     * поиск рецепта по времени готовки
     * @param cookingTime время готовки
     * @return список найденных рецептов
     * @throws DataBaseException ошибка базы данных
     */
    List<RecipeEntity> findByCookingTime(Long cookingTime) throws DataBaseException;

    /**
     * метод получения всех рецептов
     * @return список найденных рецептов
     * @throws DataBaseException ошибка базы данных
     */
    List<RecipeEntity> findAll() throws DataBaseException ;

    /**
     * метод получения рецептов по уникальному идентификатору
     * @param longList список идентификаторов
     * @return список найденных рецептов
     * @throws DataBaseException ошибка базы данных
     */
    List<RecipeEntity> findAllById(LongListImpl longList) throws DataBaseException;

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id уникальный идентификатор
     * @return логический ответ
     */
    Boolean deleteById(Long id) throws DataBaseException ;

    /**
     * метод удаления рецептов по названию
     * @param name название рецептов
     * @return логический ответ
     * @throws DataBaseException ошибка базы данных
     */
    Boolean deleteByName(String name) throws DataBaseException;

    /**
     * метод удаления рецептов по списку идентификаторов
     * @param longList список уникальных идентификаторов
     * @return логический ответ
     */
    Boolean deleteAllById(LongListImpl longList) throws DataBaseException;

}
