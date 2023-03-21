package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.image.ImageEntityImpl;
import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.StepDescriptionEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;

import java.util.Calendar;
import java.util.List;

/**
 * интерфейс сущности рецепта
 * @author KhrustalevSA
 * @since 31.01.2023
 */
public interface RecipeEntity {
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
    List<IngredientEntityImpl> getIngredientsList();

    /**
     * @return описание рецепта
     */
    String getDescription();

    /**
     * @return список изображений на странице рецепта
     */
    List<ImageEntityImpl> getImageList();

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
    List<StepDescriptionEntityImpl> getStepDescriptionList();

    /**
     * @return сложность рецепта
     */
    String getDifficulty();

    /**
     * @return список пользователей добавивших рецепт в "избранное"
     */
    List<UserEntityImpl> getUserList();
}
