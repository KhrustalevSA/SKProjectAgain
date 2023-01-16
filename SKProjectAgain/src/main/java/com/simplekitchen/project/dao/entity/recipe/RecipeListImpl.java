package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RecipeListImpl implements RecipeList {
    private List<Recipe> recipeList;

}
