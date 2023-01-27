package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.recipe.api.StepDescription;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StepDescriptionImpl implements StepDescription {
    private Long uuid;

    private String description;

    private RecipeImpl recipe;
}
