package com.simplekitchen.project.dto.entity.ingredient.api;

import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

import java.util.List;

/**
 * интерфейс ДТО ингредиента
 * @author KhrustalevSA
 * @since 31.01.2023
 */
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
     * метод получения рецепта
     * @return ДТО сущность рецепта
     */
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
