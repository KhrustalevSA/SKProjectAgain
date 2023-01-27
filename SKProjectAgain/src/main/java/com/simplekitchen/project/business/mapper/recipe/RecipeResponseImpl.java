package com.simplekitchen.project.business.mapper.recipe;

import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponse;
import lombok.Data;
import lombok.Builder;

/**
 * Класс реализующий интерфейс RecipeRequest
 * @see RecipeResponse
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
public class RecipeResponseImpl implements RecipeResponse {

    /**
     * поле статуса получения рецепта
     */
    private StatusImpl status;

    /**
     * поле сущности полученного рецепта
     */
    private RecipeList recipeList;
}