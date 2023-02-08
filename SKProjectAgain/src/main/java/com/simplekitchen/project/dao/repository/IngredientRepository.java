package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * интерфейс репозитория ингредиентов
 * CRUD репозиторий
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntityImpl,Long> {
}
