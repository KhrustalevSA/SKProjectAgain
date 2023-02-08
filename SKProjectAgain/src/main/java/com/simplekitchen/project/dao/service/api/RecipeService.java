package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;

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
    Optional<RecipeEntity> save(RecipeEntity recipeEntity);

    /**
     * метод сохранения списка рецептов
     * @param recipeEntityList список сущностей рецептов
     * @return список сохраненных рецептов
     */
    List<RecipeEntity> saveAll(List<RecipeEntity> recipeEntityList);

    /**
     * метод получения рецепта по уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Optional объект полученного рецепта
     */
    Optional<RecipeEntity> get(Long id);

    /**
     * метод получения всех рецептов
     * @return список рецептов
     */
    List<RecipeEntity> getAll();

    /**
     * метод получения рецептов по уникальному идентификатору
     * @param ids список идентификаторов
     * @return список рецептов
     */
    List<RecipeEntity> getAllById(List<Long> ids);

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id уникальный идентификатор
     * @return Boolean объект
     */
    Boolean deleteById(Long id);

    /**
     * метод удаления рецепта по его сущности
     * @param recipeEntity сущность рецепта
     * @return Boolean объект
     */
    Boolean delete(RecipeEntity recipeEntity);

    /**
     * метод удаления списка рецептов
     * @param recipeEntityList список сущностей рецепта
     * @return Boolean объект
     */
    Boolean deleteAll(List<RecipeEntity> recipeEntityList);

    /**
     * метод удаления всех рецептов
     * @return Boolean объект
     */
    Boolean deleteAll();
}
