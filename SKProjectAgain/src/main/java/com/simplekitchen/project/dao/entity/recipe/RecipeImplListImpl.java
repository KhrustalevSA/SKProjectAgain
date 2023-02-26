package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.RecipeImplList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * класс списка с классом сущности рецепта
 * @author KhrustalevSA
 * @since 26.02.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeImplListImpl implements RecipeImplList {

    /**
     * список класса сущности рецепта
     */
    private List<RecipeEntityImpl> recipeList;
}
