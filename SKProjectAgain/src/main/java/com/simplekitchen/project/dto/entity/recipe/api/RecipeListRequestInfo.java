package com.simplekitchen.project.dto.entity.recipe.api;

import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

import java.util.List;

/**
 * интерфейс списка с информацией о рецептах
 * @author KhrustalevSA
 * @since 16.03.2023
 */
public interface RecipeListRequestInfo {

     /**
      * метод получения списка рецептов
      * @return список рецептов
      */
     List<RecipeImpl> getRecipeList();

}
