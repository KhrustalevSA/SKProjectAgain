package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;

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
    List<RecipeEntityImpl> getRecipeEntityList();
}
