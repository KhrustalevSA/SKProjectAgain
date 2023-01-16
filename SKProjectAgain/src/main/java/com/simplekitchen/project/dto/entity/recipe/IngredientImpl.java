package com.simplekitchen.project.dto.entity.recipe;


import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import lombok.*;

import java.util.List;

/**
 * Класс реализующий интерфейс Ingredient
 * @see com.simplekitchen.project.dto.entity.recipe.api.Ingredient
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientImpl implements com.simplekitchen.project.dto.entity.recipe.api.Ingredient {

    /**
     *  уникальный идентификатор рецепта
     */
    private Long uuid;

    /**
     *  название ингредиента
     */
    private String name;

    /**
     *  список рецептов где используется
     */
    private List<Recipe> recipeList;

    /**
     *  средний вес ингредиента
     */
    private Double averageWeight;

    /**
     *  срок годности ингредиента
     */
    private Double expirationDate;

    /**
     *  срок годности ингредиента в холодильнике
     */
    private Double expirationDateInFridge;
}
