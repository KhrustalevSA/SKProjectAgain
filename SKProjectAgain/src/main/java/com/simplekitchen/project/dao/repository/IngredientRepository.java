package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.Ingredient.IngredientImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientImpl,Long> {
}
