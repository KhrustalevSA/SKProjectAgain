package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.Ingredient;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Ingredients")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IngredientImpl implements Ingredient {

    /**
     * уникальный идентификатор рецепта
     */
    @Id
    @Column(name = "id")
    private Long uuid;

    /**
     * название ингредиента
     */
    @Column
    private String name;

    /**
     * список рецептов где используется
     */
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "ingredientsList")

    private List<RecipeImpl> recipeList;

    /**
     * средний вес ингредиента
     */
    @Column
    private Double averageWeight;


    /**
     * срок годности ингредиента
     */
    @Column
    private Double expirationDate;

    /**
     * срок годности ингредиента в холодильнике
     */
    @Column
    private Double expirationDateInFridge;
}
