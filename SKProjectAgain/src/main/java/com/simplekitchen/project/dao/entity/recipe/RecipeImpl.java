package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.Ingredient.IngredientImpl;
import com.simplekitchen.project.dao.entity.image.ImageImpl;
import com.simplekitchen.project.dao.entity.recipe.api.Recipe;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Recipes")
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
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "recipes_ingredients",
            joinColumns = @JoinColumn(name = "recipe_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
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
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "recipes_steps",
            joinColumns = @JoinColumn(name = "recipes_id"),
            inverseJoinColumns = @JoinColumn(name = "step_id"))
    private List<StepDescriptionImpl> stepsDescription;

    @Column
    private String difficulty;

    @ManyToMany(mappedBy = "favoriteRecipeList")
    private List<UserImpl> userList;

    public RecipeImpl() {
    }

    public RecipeImpl(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public RecipeImpl(String name, List<IngredientImpl> ingredientsList, String description, List<ImageImpl> imagesList, Long cookingTime, String author,
                      Calendar publishDate, List<StepDescriptionImpl> stepsDescription, String difficulty, List<UserImpl> userList) {
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.description = description;
        this.imagesList = imagesList;
        this.cookingTime = cookingTime;
        this.author = author;
        this.publishDate = publishDate;
        this.stepsDescription = stepsDescription;
        this.difficulty = difficulty;
        this.userList = userList;
    }

    public RecipeImpl(Long id, String name, List<IngredientImpl> ingredientsList, String description, List<ImageImpl> imagesList, Long cookingTime, String author,
                      Calendar publishDate, List<StepDescriptionImpl> stepsDescription, String difficulty, List<UserImpl> userList) {
        this.id = id;
        this.name = name;
        this.ingredientsList = ingredientsList;
        this.description = description;
        this.imagesList = imagesList;
        this.cookingTime = cookingTime;
        this.author = author;
        this.publishDate = publishDate;
        this.stepsDescription = stepsDescription;
        this.difficulty = difficulty;
        this.userList = userList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long uuid) {
        this.id = uuid;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<IngredientImpl> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<IngredientImpl> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<ImageImpl> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<ImageImpl> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public Long getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(Long cookingTime) {
        this.cookingTime = cookingTime;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public Calendar getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Calendar publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public List<StepDescriptionImpl> getStepsDescription() {
        return stepsDescription;
    }

    public void setStepsDescription(List<StepDescriptionImpl> stepsDescription) {
        this.stepsDescription = stepsDescription;
    }

    @Override
    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public List<UserImpl> getUserList() {
        return userList;
    }

    public void setUserList(List<UserImpl> userList) {
        this.userList = userList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RecipeImpl)) return false;
        RecipeImpl recipe = (RecipeImpl) o;
        return Objects.equals(getId(), recipe.getId()) && Objects.equals(getName(), recipe.getName()) && Objects.equals(getIngredientsList(),
                recipe.getIngredientsList()) && Objects.equals(getDescription(), recipe.getDescription()) && Objects.equals(getImagesList(),
                recipe.getImagesList()) && Objects.equals(getCookingTime(), recipe.getCookingTime()) && Objects.equals(getAuthor(),
                recipe.getAuthor()) && Objects.equals(getPublishDate(), recipe.getPublishDate()) && Objects.equals(getStepsDescription(),
                recipe.getStepsDescription()) && Objects.equals(getDifficulty(), recipe.getDifficulty()) && Objects.equals(getUserList(), recipe.getUserList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getIngredientsList(), getDescription(), getImagesList(), getCookingTime(), getAuthor(), getPublishDate(),
                getStepsDescription(), getDifficulty(), getUserList());
    }

    @Override
    public String toString() {
        return "RecipeImpl{" +
                "uuid=" + id +
                ", name='" + name + '\'' +
                ", ingredientsList=" + ingredientsList +
                ", description='" + description + '\'' +
                ", imagesList=" + imagesList +
                ", cookingTime=" + cookingTime +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", stepsDescription=" + stepsDescription +
                ", difficulty='" + difficulty + '\'' +
                ", userList=" + userList +
                '}';
    }

    public static RecipeImpl.RecipeImplBuilder builder() {
        return new RecipeImpl.RecipeImplBuilder();
    }

    public static class RecipeImplBuilder {

        private Long id;
        private String name;
        private List<IngredientImpl> ingredientsList;
        private String description;
        private List<ImageImpl> imagesList;
        private Long cookingTime;
        private String author;
        private Calendar publishDate;
        private List<StepDescriptionImpl> stepsDescription;
        private String difficulty;
        private List<UserImpl> userList;

        RecipeImplBuilder() {
        }

        public RecipeImpl.RecipeImplBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder ingredientsList(final List<IngredientImpl> ingredientsList) {
            this.ingredientsList = ingredientsList;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder imagesList(final List<ImageImpl> imagesList) {
            this.imagesList = imagesList;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder cookingTime(final Long cookingTime) {
            this.cookingTime = cookingTime;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder author(final String author) {
            this.author = author;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder publishDate(final Calendar publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder stepsDescription(final List<StepDescriptionImpl> stepsDescription) {
            this.stepsDescription = stepsDescription;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder difficulty(final String difficulty) {
            this.difficulty = difficulty;
            return this;
        }

        public RecipeImpl.RecipeImplBuilder userList(final List<UserImpl> userList) {
            this.userList = userList;
            return this;
        }

        public RecipeImpl build() {
            return new RecipeImpl(this.id, this.name, this.ingredientsList, this.description,this.imagesList,this.cookingTime,this.author,this.publishDate,
                    this.stepsDescription,this.difficulty,this.userList);
        }

        public String toString() {
            return "UserImpl.UserImplBuilder(id=" + this.id + ", name=" + this.name + ", ingredientsList=" + this.ingredientsList + ", description=" +  this.description + ", imagesList=" + this.imagesList + ", cookingTime=" + this.cookingTime + ", author=" + this.author + ", publishDate=" + this.publishDate
                    + ", stepsDescription=" + this.stepsDescription + ", difficulty=" + this.difficulty + ", userList=" + this.userList + ")";
        }
    }

}
