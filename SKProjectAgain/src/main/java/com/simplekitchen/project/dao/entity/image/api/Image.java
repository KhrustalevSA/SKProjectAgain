package com.simplekitchen.project.dao.entity.image.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.List;

public interface Image {

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
    List<RecipeImpl> getRecipe();
}
