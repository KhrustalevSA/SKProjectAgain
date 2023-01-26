package com.simplekitchen.project.dao.entity.ingredient;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.ingredient.api.Ingredient;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ingredients")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IngredientImpl implements Ingredient, Serializable {

    /**
     * уникальный идентификатор рецепта
     */
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IngredientImpl)) return false;
        IngredientImpl that = (IngredientImpl) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getRecipeList(), that.getRecipeList()) && Objects.equals(getAverageWeight(), that.getAverageWeight()) && Objects.equals(getExpirationDate(), that.getExpirationDate()) && Objects.equals(getExpirationDateInFridge(), that.getExpirationDateInFridge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRecipeList(), getAverageWeight(), getExpirationDate(), getExpirationDateInFridge());
    }
}
