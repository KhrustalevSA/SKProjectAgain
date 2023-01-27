package com.simplekitchen.project.dao.entity.ingredient.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;

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

    RecipeImpl getRecipe();

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
