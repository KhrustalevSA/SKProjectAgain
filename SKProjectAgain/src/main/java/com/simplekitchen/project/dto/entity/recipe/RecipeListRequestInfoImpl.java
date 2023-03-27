package com.simplekitchen.project.dto.entity.recipe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeListRequestInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * класс списка информации о рецепте
 * @author KhrustalevSA
 * @since 16.03.2023
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeListRequestInfoImpl implements RecipeListRequestInfo {
    private List<RecipeImpl> recipeList;

    @Override
    public List<RecipeImpl> getRecipeList() {
        if(recipeList == null) {
            recipeList = new ArrayList<>();
        }
        return recipeList;
    }
}
