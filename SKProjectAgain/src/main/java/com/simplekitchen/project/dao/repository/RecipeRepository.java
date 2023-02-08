package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс репозитория рецептов
 * CRUD репозиторий
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntity, Long> {
}
