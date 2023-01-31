package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.recipe.api.StepDescription;
import lombok.Builder;
import lombok.Data;

/**
 * ДТО класс шага приготовления
 */
@Data
@Builder
public class StepDescriptionImpl implements StepDescription {

    /**
     * уникальный идентификатор шага
     */
    private Long id;

    /**
     * номер шага
     */
    private Long stepNumber;

    /**
     * описание шага
     */
    private String description;

    /**
     * рецепт в котором используется
     */
    private RecipeImpl recipe;
}
