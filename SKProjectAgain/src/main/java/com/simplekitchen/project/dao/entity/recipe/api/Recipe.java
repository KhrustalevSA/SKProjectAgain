package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.image.ImageImpl;
import com.simplekitchen.project.dao.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dao.entity.recipe.StepDescriptionImpl;
import com.simplekitchen.project.dao.entity.user.UserImpl;

import java.util.Calendar;
import java.util.List;

/**
 * интерфейс сущности рецепта
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface Recipe {
    /**
     * @return уникальный идентификатор рецепта
     */
    Long getId();

    /**
     * @return название рецепта
     */
    String getName();

    /**
     * @return список нужных ингредиентов для приготовления
     */
    List<IngredientImpl> getIngredientsList();

    /**
     * @return описание рецепта
     */
    String getDescription();

    /**
     * @return список изображений на странице рецепта
     */
    List<ImageImpl> getImagesList();

    /**
     * @return время готовки рецепта
     */
    Long getCookingTime();

    /**
     * @return имя автора рецепта
     */
    String getAuthor();

    /**
     * @return дату публикации рецепта
     */
    Calendar getPublishDate();

    /**
     * @return список описаний правильности действий на шагах готовки
     */
    List<StepDescriptionImpl> getStepsDescription();

    /**
     * @return сложность рецепта
     */
    String getDifficulty();

    /**
     * @return список пользователей добавивших рецепт в "избранное"
     */
    List<UserImpl> getUserList();
}
