package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;

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
     * @param recipe
     * @return сохраненный Optional объект рецепта
     */
    Optional<Recipe> save(Recipe recipe);

    /**
     * метод сохранения списка рецептов
     * @param recipeList
     * @return список сохраненных рецептов
     */
    List<Recipe> saveAll(List<Recipe> recipeList);

    /**
     * метод получения рецпта по уникальному идентификатору
     * @param id
     * @return Optional объект полученного рецепта
     */
    Optional<Recipe> get(Long id);

    /**
     * метод получения всех рецептов
     * @return список рецептов
     */
    List<Recipe> getAll();

    /**
     * метод получения рецептов по уникальному идентификатору
     * @param ids
     * @return список рецептов
     */
    List<Recipe> getAllById(List<Long> ids);

    /**
     * метод удаления рецепта по его уникальному идентификатору
     * @param id
     * @return Boolean объект
     */
    Boolean deleteById(Long id);

    /**
     * метод удаления рецепта по его сущности
     * @param recipe
     * @return Boolean объект
     */
    Boolean delete(Recipe recipe);

    /**
     * метод удаления списка рецептов
     * @param recipeList
     * @return Boolean объект
     */
    Boolean deleteAll(List<Recipe> recipeList);

    /**
     * метод удаления всех рецептов
     * @return Boolean объект
     */
    Boolean deleteAll();
}
