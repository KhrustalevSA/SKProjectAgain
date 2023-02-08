package com.simplekitchen.project.dao.entity.ingredient.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;

/**
 * интерфейс сущности ингредиента
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface IngredientEntity {

    /**
     * @return уникальный идентификатор рецепта
     */
    Long getId();

    /**
     * @return название ингредиента
     */
    String getName();

    /**
     * @return сущность рецепта к которому привязан ингредиент
     */
    RecipeEntityImpl getRecipe();

    /**
     * @return средний вес ингредиента
     */
    Double getAverageWeight();


    /**
     * @return срок годности ингредиента
     */
    Double getExpirationDate();

    /**
     * @return срок годности ингредиента в холодильнике
     */
    Double getExpirationDateInFridge();
}
