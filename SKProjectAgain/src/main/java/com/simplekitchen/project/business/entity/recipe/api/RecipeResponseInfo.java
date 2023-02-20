package com.simplekitchen.project.business.entity.recipe.api;


import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;

import java.util.List;

/**
 * Интерфейс для ответа на запроса рецепта пользователем
 * @author KhrustalevSA
 * @since 09.10.2022
 */
public interface RecipeResponseInfo {
    /**
     * @return статус ответа
     */
    StatusImpl getStatus();

    /**
     * @return сущность рецепта
     */
    List<Recipe> getRecipeList();
}
