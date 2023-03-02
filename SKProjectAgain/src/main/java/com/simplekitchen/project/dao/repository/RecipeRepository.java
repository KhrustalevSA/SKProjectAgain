package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
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

    /**
     * поиск рецепта по названию в базе данных
     * @param name название рецепта
     * @return Optional объект списка найденных рецептов
     */
    Optional<List<RecipeEntity>> findByName(String name);

    /**
     * поиск рецепта по сложности в базе данных
     * @param difficulty сложность рецепта
     * @return Optional объект списка найденных рецептов
     */
    Optional<List<RecipeEntity>> findByDifficulty(String difficulty);

    /**
     * поиск рецепта по времени приготовления в базе данных
     * @param time время приготовления рецепта
     * @return Optional объект списка найденных рецептов
     */
    Optional<List<RecipeEntity>> findByCookingTime(Long time);

    /**
     * метод удаления рецептов по названию
     * @param name название рецепта
     */
    void deleteAllByName(String name);
}


