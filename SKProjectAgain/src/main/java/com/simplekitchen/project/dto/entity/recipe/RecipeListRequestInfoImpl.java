package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeListRequestInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * класс списка информации о рецепте
 * @author KhrustalevSA
 * @since 16.03.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeListRequestInfoImpl implements RecipeListRequestInfo {
    private List<RecipeImpl> recipeList;
}
