package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.image.ImageImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Recipes")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeImpl implements Recipe {
    /**
     * уникальный идентификатор рецепта
     */
    @Id
    @Column
    private Long uuid;

    /**
     * название рецепта
     */
    @Column
    private String name;

    /**
     * список нужных ингредиентов для приготовления
     */
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
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
    private Date publishDate;

    /**
     * список описаний правильности действий на шагах готовки
     */
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "recipes_steps",
            joinColumns = @JoinColumn(name = "recipes_id"),
            inverseJoinColumns = @JoinColumn(name = "step_id"))
    @ToString.Exclude
    private List<StepDescriptionImpl> stepsDescription;

    @Column
    private String difficulty;

    @ManyToMany(mappedBy = "favoriteRecipeList")
    @ToString.Exclude
    private List<UserImpl> userList;
}
