package com.simplekitchen.project.business.entity.recipe;

import com.simplekitchen.project.business.entity.recipe.api.Recipe;
import com.simplekitchen.project.business.entity.recipe.api.RecipeList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс для сущности списка рецептов
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeListImpl implements RecipeList {
    /**
     * список рецептов
     */
    private List<Recipe> recipeList;
}
