package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    Optional<Recipe> save(Recipe recipe);

    List<Recipe> saveAll(List<Recipe> recipeList);

    Optional<Recipe> get(Long id);

    List<Recipe> getAll();

    List<Recipe> getAllById(List<Long> ids);

    Boolean deleteById(Long id);

    Boolean delete(Recipe recipe);

    Boolean deleteAll(List<Recipe> recipeList);

    Boolean deleteAll();
}
