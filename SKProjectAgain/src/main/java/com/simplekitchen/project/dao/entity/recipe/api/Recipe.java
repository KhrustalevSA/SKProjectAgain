package com.simplekitchen.project.dao.entity.recipe.api;

import com.simplekitchen.project.dao.entity.image.ImageImpl;
import com.simplekitchen.project.dao.entity.Ingredient.IngredientImpl;
import com.simplekitchen.project.dao.entity.recipe.StepDescriptionImpl;
import com.simplekitchen.project.dao.entity.user.UserImpl;

import java.util.Calendar;
import java.util.List;

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

    // /**
    // * @return объект автора рецепта
    // */
    // Author getAuthor();

    /**
     * @return дату публикации рецепта
     */
    Calendar getPublishDate();

    /**
     * @return список описаний правильности действий на шагах готовки
     */
    List<StepDescriptionImpl> getStepsDescription();

    String getDifficulty();

    List<UserImpl> getUserList();
}
