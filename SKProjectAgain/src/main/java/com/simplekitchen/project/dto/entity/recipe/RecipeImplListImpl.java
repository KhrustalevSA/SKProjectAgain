package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.recipe.api.RecipeImplList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImplListImpl implements RecipeImplList {
    private List<RecipeImpl> recipeList;
}
