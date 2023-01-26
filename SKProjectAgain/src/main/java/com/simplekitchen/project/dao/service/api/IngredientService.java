package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.ingredient.IngredientImpl;

import java.util.List;
import java.util.Optional;

public interface IngredientService {
    Optional<IngredientImpl> save(IngredientImpl ingredient);

    List<IngredientImpl> saveAll(List<IngredientImpl> ingredientList);

    Optional<IngredientImpl> get(Long id);

    List<IngredientImpl> getAll();

    List<IngredientImpl> getAllById(List<Long> ids);

    Boolean deleteById(Long id);

    Boolean delete(IngredientImpl ingredient);

    Boolean deleteAll(List<IngredientImpl> ingredientList);

    Boolean deleteAll();
}
