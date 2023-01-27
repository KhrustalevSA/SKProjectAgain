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

    private Long id;

    private String name;

    private List<IngredientImpl> ingredientList;

    private String description;

    private List<ImageImpl> imageList;

    private Long cookingTime;

    private String author;

    private Calendar publishDate;

    private List<StepDescriptionImpl> stepDescriptionList;

    private String difficulty;

    private List<UserImpl> userList;
}
