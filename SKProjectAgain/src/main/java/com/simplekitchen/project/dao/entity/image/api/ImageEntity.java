package com.simplekitchen.project.dao.entity.image.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;

/**
 *
 */
public interface ImageEntity {

    /**
     * @return путь к изображению
     */
    String getPath();

    /**
     * @return url изображения
     */
    String getUrl();

    /**
     * @return Список рецптов
     */
    RecipeEntityImpl getRecipe();
}
