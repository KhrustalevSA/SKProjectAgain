package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Optional<Recipe> save(Recipe recipe);

    List<RecipeImpl> saveAll(List<RecipeImpl> recipeList);

    Optional<RecipeImpl> get(Long id);

    List<RecipeImpl> getAll();

    List<RecipeImpl> getAllById(List<Long> ids);

    Boolean deleteById(Long id);

    Boolean delete(RecipeImpl recipe);

    Boolean deleteAll(List<RecipeImpl> recipeList);

    Boolean deleteAll();
}
