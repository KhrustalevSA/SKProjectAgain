package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;

import java.util.List;

/**
 * Интерфейс для отслеживанния шагов приготовления рецепта
 * @author KhrustalevSA
 * @since 22.01.2023
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
