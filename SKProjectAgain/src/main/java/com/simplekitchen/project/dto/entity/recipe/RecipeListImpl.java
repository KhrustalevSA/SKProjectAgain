package com.simplekitchen.project.dto.entity.recipe;


import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import lombok.*;

import java.util.List;

/**
 * Класс для сущности списка рецептов
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
public class RecipeListImpl implements RecipeList {
    /**
     * список рецептов
     */
    private List<Recipe> recipeList;
}
