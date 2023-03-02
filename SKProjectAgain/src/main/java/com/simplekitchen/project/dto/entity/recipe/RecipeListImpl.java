package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.recipe.api.RecipeList;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * класс содержащий список рецептов
 * @author KhrustalevSA
 * @since 02.03.2023
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class RecipeListImpl implements RecipeList {

    /**
     * список рецептов
     */
    private List<Recipe> recipeList;

    /**
     * метод получения списка рецептов с определением списка если его не существует
     * @return список рецептов
     */
    @Override
    public List<Recipe> getRecipeList() {
        if (recipeList == null) {
            return recipeList = new ArrayList<>();
        }
        return recipeList;
    }
}
