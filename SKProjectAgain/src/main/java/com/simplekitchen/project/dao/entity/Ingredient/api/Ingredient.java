package com.simplekitchen.project.dao.entity.Ingredient.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.List;

public interface Ingredient {

    /**
     * @return уникальный идентификатор рецепта
     */

    Long getId();

    /**
     * @return название ингредиента
     */
    String getName();

    /**
     * @return список рецептов где используется
     */
    List<RecipeImpl> getRecipeList();

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
