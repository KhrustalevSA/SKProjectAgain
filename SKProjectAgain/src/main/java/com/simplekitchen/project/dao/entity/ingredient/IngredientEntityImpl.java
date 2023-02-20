package com.simplekitchen.project.dao.entity.ingredient;

import com.simplekitchen.project.dao.entity.ingredient.api.IngredientEntity;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * класс сущности ингредиента
 * @author KhrustelevSA
 * @since 31.01.2023
 */
@Entity
@Table(name = "ingredient")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IngredientEntityImpl implements IngredientEntity, Serializable {

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
     * поле сущности рецепта к которому привязан ингредиент
     */
    @ManyToOne
    @JoinColumn(name = "ingredients")
    private RecipeEntityImpl recipe;

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
        if (!(o instanceof IngredientEntityImpl)) return false;
        IngredientEntityImpl that = (IngredientEntityImpl) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getName(), that.getName()) && Objects.equals(getRecipe(), that.getRecipe()) && Objects.equals(getAverageWeight(), that.getAverageWeight()) && Objects.equals(getExpirationDate(), that.getExpirationDate()) && Objects.equals(getExpirationDateInFridge(), that.getExpirationDateInFridge());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getRecipe(), getAverageWeight(), getExpirationDate(), getExpirationDateInFridge());
    }
}
