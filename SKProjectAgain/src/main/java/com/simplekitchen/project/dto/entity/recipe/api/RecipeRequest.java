package com.simplekitchen.project.dto.entity.recipe.api;

/**
 * Интерфейс для запроса рецепта
 * @author KhrustalevSA
 * @since 09.10.2022
 */
public interface RecipeRequest {

    /**
     * @return уникальный идентификатор запроса рецепта
     */
    String getUuid();

    /**
     * @return название рецепта
     */
    String getName();

}
