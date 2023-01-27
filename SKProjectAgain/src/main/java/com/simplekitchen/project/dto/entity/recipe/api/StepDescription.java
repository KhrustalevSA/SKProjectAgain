package com.simplekitchen.project.dto.entity.recipe.api;

import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

public interface StepDescription {
    Long getUuid();
    String getDescription();
    RecipeImpl getRecipe();
}
