package com.simplekitchen.project.dto.entity.recipe;

import com.simplekitchen.project.dto.entity.image.ImageImpl;
import com.simplekitchen.project.dto.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dto.entity.recipe.api.Recipe;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.List;

/**
 * ДТО класс рецепта
 * @see Recipe
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecipeImpl implements Recipe {

    /**
     * уникальный идентификатор рецепта
     */
    private Long id;

    /**
     * название рецепта
     */
    private String name;

    /**
     * список ингредиентов для приготовления
     */
    private List<IngredientImpl> ingredientList;

    /**
     * описание рецепта
     */
    private String description;

    /**
     * список изображений
     */
    private List<ImageImpl> imageList;

    /**
     * время приготовления
     */
    private Long cookingTime;

    /**
     * автор рецепта
     */
    private String author;

    /**
     * дата публикации
     */
    private Calendar publishDate;

    /**
     * список шагов приготовления
     */
    private List<StepDescriptionImpl> stepDescriptionList;

    /**
     * сложность рецепта
     */
    private String difficulty;

}
