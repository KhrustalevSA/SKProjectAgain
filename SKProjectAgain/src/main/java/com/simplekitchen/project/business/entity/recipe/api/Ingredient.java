package com.simplekitchen.project.business.entity.recipe.api;

import java.util.List;

/**
 * Интерфейс для сущности ингредиента
 * @author KhrustalevSA
 * @since 09.10.2022
 */
public interface Ingredient {

    /**
     * @return уникальный идентификатор рецепта
     */

    Long getUuid();

    /**
     * @return название ингредиента
     */
    String getName();

    /**
     * @return список рецептов где используется
     */
    List<Recipe> getRecipeList();

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
