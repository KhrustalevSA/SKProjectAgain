package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.ingredient.IngredientImpl;
import com.simplekitchen.project.dao.entity.image.ImageImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Recipes")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RecipeImpl implements Recipe, Serializable {
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
    private List<IngredientImpl> ingredientsList;

    /**
     * описание рецепта
     */
    @Column
    private String description;

    /**
     * список изображений на странице рецепта
     */
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "recipes_images",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id"))
    @ToString.Exclude
    private List<ImageImpl> imagesList;

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
     * список описаний правильности действий на шагах готовки
     */
    @OneToMany
    @ToString.Exclude
    private List<StepDescriptionImpl> stepsDescription;

    @Column
    private String difficulty;

    @ManyToMany(mappedBy = "favoriteRecipeList")
    @ToString.Exclude
    private List<UserImpl> userList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeImpl)) return false;
        RecipeImpl recipe = (RecipeImpl) o;
        return Objects.equals(getId(), recipe.getId()) && Objects.equals(getName(), recipe.getName()) && Objects.equals(getIngredientsList(), recipe.getIngredientsList()) && Objects.equals(getDescription(), recipe.getDescription()) && Objects.equals(getImagesList(), recipe.getImagesList()) && Objects.equals(getCookingTime(), recipe.getCookingTime()) && Objects.equals(getAuthor(), recipe.getAuthor()) && Objects.equals(getPublishDate(), recipe.getPublishDate()) && Objects.equals(getStepsDescription(), recipe.getStepsDescription()) && Objects.equals(getDifficulty(), recipe.getDifficulty()) && Objects.equals(getUserList(), recipe.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIngredientsList(), getDescription(), getImagesList(), getCookingTime(), getAuthor(), getPublishDate(), getStepsDescription(), getDifficulty(), getUserList());
    }
}
