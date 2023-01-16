package com.simplekitchen.project.business.entity.recipe;

import com.simplekitchen.project.business.entity.image.api.Image;
import com.simplekitchen.project.business.entity.recipe.api.Ingredient;
import com.simplekitchen.project.business.entity.recipe.api.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


/**
 * Класс реализующий интерфейс Recipe
 * @see Recipe
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImpl implements Recipe {

    /**
     * поле уникального идентификатора рецепта
     */
    private Long uuid;

    /**
     * поле названия рецепта
     */
    private String name;

    /**
     * поле списка используемых в рецепте ингредиентов
     */
    private List<Ingredient> ingredientsList;

    /**
     * поле описания рецепта
     */
    private String description;

    /**
     * поле списка изображений на странице рецептов
     */
    private List<Image> imagesList;

    /**
     * список описаний правильности действий на шагах готовки
     */
    private List<String> stepsDescription;

    /**
     * время готовки рецепта
     */
    private Long cookingTime;

    /**
     * имя автора рецепта
     */
    private String author;

    /**
     * дату публикации рецепта
     */
    private Date publishDate;

    /**
     * Сложность рецепта
     */
    private String difficulty;
}
