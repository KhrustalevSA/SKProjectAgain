package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;

import java.util.List;

/**
 * интерфейс списка класса рецептов
 * @author KhrustalevSA
 * @since 26.02.2023
 */
public interface RecipeImplList {
    /**
     * метод возвращающий список рецептов
     * @return список рецептов
     */
    List<RecipeEntityImpl> getRecipeList();
}
