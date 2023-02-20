package com.simplekitchen.project.business.entity.recipe;

import com.simplekitchen.project.business.entity.common.StatusImpl;
import com.simplekitchen.project.business.entity.recipe.api.RecipeResponseInfo;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Класс реализующий интерфейс RecipeRequestInfo
 * @see RecipeResponseInfo
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeResponseInfoImpl implements RecipeResponseInfo {

    /**
     * поле статуса получения рецепта
     */
    private StatusImpl status;

    /**
     * поле сущности полученного рецепта
     */
    private List<Recipe> recipeList;
}
