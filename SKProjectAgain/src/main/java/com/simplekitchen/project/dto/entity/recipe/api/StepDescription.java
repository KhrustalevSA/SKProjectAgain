package com.simplekitchen.project.dto.entity.recipe.api;

import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;

/**
 * Интерфейс для отслеживанния шагов приготовления рецепта
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface StepDescription {
    /**
     * @return уникальный идентификатор шага
     */
    Long getId();

    /**
     * @return номер шага
     */
    Long getStepNumber();

    /**
     * @return описание шага
     */
    String getDescription();

    /**
     * @return рецепт для которого написан шага
     */
    RecipeImpl getRecipe();
}
