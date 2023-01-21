package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<RecipeImpl, Long> {
}
