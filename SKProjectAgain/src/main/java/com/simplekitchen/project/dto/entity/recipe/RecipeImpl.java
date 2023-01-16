package com.simplekitchen.project.dto.entity.recipe;


import com.simplekitchen.project.dto.entity.image.api.Image;
import com.simplekitchen.project.dto.entity.recipe.api.Ingredient;
import lombok.*;

import java.util.Date;
import java.util.List;

/**
 * Класс реализующий интерфейс Recipe
 * @see com.simplekitchen.project.dto.entity.recipe.api.Recipe
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImpl implements com.simplekitchen.project.dto.entity.recipe.api.Recipe {

    /**
     * поле уникального идентификатора рецепта
     */
    private String uuid;

    /**
     * поле названия рецепта
     */
    private String name;

    /**
     * поле списка используемых в рецепте ингредиентов
     */
    private List<Ingredient> ingredients;

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
    private List<String> stepDescription;

    /**
     * время готовки рецепта
     */
    private Long cookingTime;

    /**
     * имя автора рецепта
     */
    private String author;

    /**
     *  дату публикации рецепта
     */
    private Date publishDate;

    private String difficulty;
}
