package com.simplekitchen.project.business.entity.recipe;

import com.simplekitchen.project.business.entity.recipe.api.Ingredient;
import com.simplekitchen.project.business.entity.recipe.api.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс реализующий интерфейс Ingredient
 * @see Ingredient
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientImpl implements Ingredient {

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
