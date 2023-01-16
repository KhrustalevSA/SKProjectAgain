package com.simplekitchen.project.dto.entity.recipe;


import com.simplekitchen.project.dto.entity.recipe.api.RecipeRequest;
import lombok.*;

/**
 * Класс реализующий интерфейс RecipeRequest
 * @see RecipeRequest
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequestImpl implements RecipeRequest {

    /**
     * поле уникального идентификатора рецепта
     */
    private String uuid;

    /**
     * поле названия рецепта
     */
    private String name;
}
