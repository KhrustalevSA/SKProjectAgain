package com.simplekitchen.project.dto.entity.ingredient;


import com.simplekitchen.project.dto.entity.ingredient.api.Ingredient;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientImpl implements Ingredient {

    private Long id;

    private String name;

    private RecipeImpl recipe;

    private Double averageWeight;

    private Double expirationDate;

    private Double expirationDateInFridge;
}
