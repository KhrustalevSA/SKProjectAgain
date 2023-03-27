package com.simplekitchen.project.dto.entity.recipe;


import com.simplekitchen.project.dto.entity.recipe.api.RecipeRequestInfo;
import lombok.*;

/**
 * Класс реализующий интерфейс RecipeRequestInfo
 * @see RecipeRequestInfo
 * @author KhrustalevSA
 * @since 03.10.2022
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequestInfoImpl implements RecipeRequestInfo {

    /**
     * поле уникального идентификатора рецепта
     */
    private Long id;

    /**
     * поле названия рецепта
     */
    private String name;
}
