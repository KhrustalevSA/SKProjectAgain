package com.simplekitchen.project.dto.entity.recipe.api;

/**
 * Интерфейс для запроса рецепта
 * @author KhrustalevSA
 * @since 09.10.2022
 */
public interface RecipeRequestInfo {

    /**
     * @return уникальный идентификатор запроса рецепта
     */
    Long getId();

    /**
     * @return название рецепта
     */
    String getName();

}
