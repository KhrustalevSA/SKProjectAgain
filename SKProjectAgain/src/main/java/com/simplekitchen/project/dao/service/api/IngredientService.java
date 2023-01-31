package com.simplekitchen.project.dao.service.api;

import com.simplekitchen.project.dao.entity.ingredient.IngredientImpl;

import java.util.List;
import java.util.Optional;

/**
 * интерфейс сервиса ингредиентов
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface IngredientService {

    /**
     * метод сохранения ингредиента
     * @param ingredient
     * @return сохраненный Optional объект ингредиента
     */
    Optional<IngredientImpl> save(IngredientImpl ingredient);

    /**
     * метод сохранения списка ингредиентов
     * @param ingredientList
     * @return список сохраненных ингредиентов
     */
    List<IngredientImpl> saveAll(List<IngredientImpl> ingredientList);

    /**
     * метод получения ингредиента по уникальному идентификатору
     * @param id
     * @return Optional объект полученного ингредиента
     */
    Optional<IngredientImpl> get(Long id);

    /**
     * метод получения всех ингредиентов
     * @return список ингредиентов
     */
    List<IngredientImpl> getAll();

    /**
     * метод получения ингредиентов по уникальному идентификатору
     * @param ids
     * @return список ингредиентов
     */
    List<IngredientImpl> getAllById(List<Long> ids);

    /**
     * метод удаления ингредиента по его уникальному идентификатору
     * @param id
     * @return Boolean объект
     */
    Boolean deleteById(Long id);

    /**
     * метод удаления ингредиента по его сущности
     * @param ingredient
     * @return Boolean объект
     */
    Boolean delete(IngredientImpl ingredient);

    /**
     * метод удаления списка ингредиентов
     * @param ingredientList
     * @return Boolean объект
     */
    Boolean deleteAll(List<IngredientImpl> ingredientList);

    /**
     * метод удаления всех ингредиентов
     * @return Boolean объект
     */
    Boolean deleteAll();
}
