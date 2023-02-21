package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
