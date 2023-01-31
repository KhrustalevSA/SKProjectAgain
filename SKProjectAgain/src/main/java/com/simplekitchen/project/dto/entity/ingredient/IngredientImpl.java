package com.simplekitchen.project.dto.entity.ingredient;


import com.simplekitchen.project.dto.entity.ingredient.api.Ingredient;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import lombok.Builder;
import lombok.Data;

/**
 * класс ДТО ингредиента
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Data
@Builder
public class IngredientImpl implements Ingredient {

    /**
     * уникальный идентификатор ингредиента
     */
    private Long id;

    /**
     * название ингредиента
     */
    private String name;

    /**
     * сущность рецепта в котором используется
     */
    private RecipeImpl recipe;

    /**
     * средний вес ингредента
     */
    private Double averageWeight;

    /**
     * срок годности
     */
    private Double expirationDate;

    /**
     * срок годности в холодильнике
     */
    private Double expirationDateInFridge;
}