package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * интерфейс репозитория рецептов
 * CRUD репозиторий
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Repository
public interface RecipeRepository extends CrudRepository<RecipeEntityImpl, Long> {
    Optional<List<RecipeEntityImpl>> findByName(String name);
    Optional<List<RecipeEntityImpl>> findByDifficulty(String difficulty);
    Optional<List<RecipeEntityImpl>> findByCookingTime(Long time);
    void deleteAllByName(String name);
}


