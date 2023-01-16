package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.List;

public interface StepDescription {
    Long getUuid();
    String getDescription();
    List<RecipeImpl> getRecipesList();
}
