package com.simplekitchen.project.dto.entity.recipe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeResponseInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий интерфейс RecipeRequestInfo
 * @see RecipeResponseInfo
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeResponseInfoImpl implements RecipeResponseInfo {

    /**
     * поле статуса получения рецепта
     */
    private StatusImpl status;

    /**
     * поле сущности полученного рецепта
     */
    private List<Recipe> recipeList;

    @Override
    public StatusImpl getStatus() {
        return status;
    }

    @Override
    public List<Recipe> getRecipeList() {
        if (recipeList == null) {
            recipeList = new ArrayList<>();
        }
        return recipeList;
    }
}
