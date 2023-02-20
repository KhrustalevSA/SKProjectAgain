package com.simplekitchen.project.dto.entity.recipe.api;

import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

import java.util.List;

/**
 * Интерфейс для сущности списка рецептов
 * @author KhrustalevSA
 * @since 16.10.2022
 */
public interface RecipeList {
    /**
     * @return список рецептов
     */
    List<Recipe> getRecipeList();
}
