package com.simplekitchen.project.dto.entity.recipe.api;


import com.simplekitchen.project.dto.entity.image.ImageImpl;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.StepDescriptionImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;

import java.util.Calendar;
import java.util.List;

/**
 * Интерфейс для сущности рецепта
 * @author KhrustalevSA
 * @since 09.10.2022
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
    List<IngredientImpl> getIngredientList();

    /**
     * @return описание рецепта
     */
    String getDescription();

    /**
     * @return список изображений на странице рецепта
     */
    List<ImageImpl> getImageList();

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
    List<StepDescriptionImpl> getStepDescriptionList();

    /**
     * @return сложность рецепта
     */
    String getDifficulty();

}
