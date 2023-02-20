package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.ingredient.IngredientEntityImpl;
import com.simplekitchen.project.dao.entity.image.ImageEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.api.RecipeEntity;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

/**
 * класс сущности рецепта
 * @author KhrustalevSA
 * @since 22.01.2023
 */
@Entity
@Table(name = "recipe")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntityImpl implements RecipeEntity, Serializable {

    /**
     * уникальный идентификатор рецепта
     */
    @Id
    @Column
    @GeneratedValue
    private Long id;

    /**
     * название рецепта
     */
    @Column
    private String name;

    /**
     * список нужных ингредиентов для приготовления
     */
    @OneToMany
    @ToString.Exclude
    private List<IngredientEntityImpl> ingredientsList;

    /**
     * описание рецепта
     */
    @Column
    private String description;

    /**
     * список изображений
     */
    @OneToMany
    @ToString.Exclude
    private List<ImageEntityImpl> imagesList;

    /**
     * время готовки рецепта
     */
    @Column
    private Long cookingTime;

    /**
     * имя автора рецепта
     */
    @Column
    private String author;

    /**
     * дату публикации рецепта
     */
    @Column
    private Calendar publishDate;

    /**
     * список шагов рецепта
     */
    @OneToMany
    @ToString.Exclude
    private List<StepDescriptionEntityImpl> stepsDescription;

    /**
     * сложность рецепта
     */
    @Column
    private String difficulty;

    /**
     * список пользователей добавивших рецепт в избранное
     */
    @ManyToMany(mappedBy = "favoriteRecipeList")
    @ToString.Exclude
    private List<UserEntityImpl> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeEntityImpl)) return false;
        RecipeEntityImpl recipe = (RecipeEntityImpl) o;
        return Objects.equals(getId(), recipe.getId()) && Objects.equals(getName(), recipe.getName()) && Objects.equals(getIngredientsList(), recipe.getIngredientsList()) && Objects.equals(getDescription(), recipe.getDescription()) && Objects.equals(getImagesList(), recipe.getImagesList()) && Objects.equals(getCookingTime(), recipe.getCookingTime()) && Objects.equals(getAuthor(), recipe.getAuthor()) && Objects.equals(getPublishDate(), recipe.getPublishDate()) && Objects.equals(getStepsDescription(), recipe.getStepsDescription()) && Objects.equals(getDifficulty(), recipe.getDifficulty()) && Objects.equals(getUserList(), recipe.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIngredientsList(), getDescription(), getImagesList(), getCookingTime(), getAuthor(), getPublishDate(), getStepsDescription(), getDifficulty(), getUserList());
    }
}
