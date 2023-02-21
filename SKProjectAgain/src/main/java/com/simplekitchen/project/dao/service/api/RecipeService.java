package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.common.entity.LongListImpl;
import com.simplekitchen.project.dao.entity.common.entity.api.LongList;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImplListImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeListImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeList;
import com.simplekitchen.project.dao.exception.DataBaseException;
import com.simplekitchen.project.dao.service.RecipeServiceImpl;

import java.util.List;
import java.util.Optional;

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
    RecipeEntity save(RecipeEntityImpl recipeEntity) throws DataBaseException ;

    /**
     * метод сохранения списка рецептов
     * @param recipeList список сущностей рецептов
     * @return список сохраненных рецептов
     */
    RecipeList saveAll(RecipeImplListImpl recipeList) throws DataBaseException;

    /**
     * метод получения рецепта по уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Optional объект полученного рецепта
     */
    RecipeEntity findById(Long id) throws DataBaseException;

    RecipeList findByName(String name) throws DataBaseException ;

    RecipeList findByDifficulty(String difficulty) throws DataBaseException;

    RecipeList findByCookingTime(Long cookingTime) throws DataBaseException;

    /**
     * метод получения всех рецептов
     * @return список рецептов
     */
    RecipeList findAll() throws DataBaseException ;

    /**
     * метод получения рецептов по уникальному идентификатору
     * @param ids список идентификаторов
     * @return список рецептов
     */
    RecipeList findAllById(LongListImpl longList) throws DataBaseException;

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Boolean объект
     */
    Boolean deleteById(Long id) throws DataBaseException ;

    Boolean deleteByName(String name) throws DataBaseException;

    /**
     * метод удаления списка рецептов
     * @param recipeEntityList список сущностей рецепта
     * @return Boolean объект
     */
    Boolean deleteAllById(LongListImpl longList) throws DataBaseException;

}
