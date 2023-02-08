package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeList;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * класс список пользователей
 * @author KhrustalevSA
 * @since 22.01.2023
 */
@Data
@Builder
public class RecipeListImpl implements RecipeList {

    /**
     * поле списка рецептов
     */
    private List<RecipeEntity> recipeEntityList;
}
