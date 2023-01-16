package com.simplekitchen.project.business.entity.recipe.api;

import com.simplekitchen.project.business.entity.image.api.Image;

import java.util.Date;
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
    Long getUuid();

    /**
     * @return название рецепта
     */
    String getName();

    /**
     * @return список нужных ингредиентов для приготовления
     */
    List<Ingredient> getIngredientsList();

    /**
     * @return описание рецепта
     */
    String getDescription();

    /**
     * @return список изображений на странице рецепта
     */
    List<Image> getImagesList();

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
    Date getPublishDate();

    /**
     * @return список описаний правильности действий на шагах готовки
     */
    List<String> getStepsDescription();

    String getDifficulty();

}
