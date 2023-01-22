package com.simplekitchen.project.dao.entity.Ingredient;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.Ingredient.api.Ingredient;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Ingredients")
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
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public List<RecipeImpl> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<RecipeImpl> recipeList) {
        this.recipeList = recipeList;
    }

    @Override
    public Double getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(Double averageWeight) {
        this.averageWeight = averageWeight;
    }

    @Override
    public Double getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Double expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public Double getExpirationDateInFridge() {
        return expirationDateInFridge;
    }

    public void setExpirationDateInFridge(Double expirationDateInFridge) {
        this.expirationDateInFridge = expirationDateInFridge;
    }

    @Override
    public String toString() {
        return "IngredientImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", recipeList=" + recipeList +
                ", averageWeight=" + averageWeight +
                ", expirationDate=" + expirationDate +
                ", expirationDateInFridge=" + expirationDateInFridge +
                '}';
    }

    public IngredientImpl() {
    }

    public IngredientImpl(String name) {
        this.name = name;
    }

    public IngredientImpl(String name, List<RecipeImpl> recipeList, Double averageWeight, Double expirationDate, Double expirationDateInFridge) {
        this.name = name;
        this.recipeList = recipeList;
        this.averageWeight = averageWeight;
        this.expirationDate = expirationDate;
        this.expirationDateInFridge = expirationDateInFridge;
    }

    public IngredientImpl(Long id, String name, List<RecipeImpl> recipeList, Double averageWeight, Double expirationDate, Double expirationDateInFridge) {
        this.id = id;
        this.name = name;
        this.recipeList = recipeList;
        this.averageWeight = averageWeight;
        this.expirationDate = expirationDate;
        this.expirationDateInFridge = expirationDateInFridge;
    }

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

    public static IngredientImpl.IngredientImplBuilder builder() {
        return new IngredientImpl.IngredientImplBuilder();
    }

    public static class IngredientImplBuilder {
        private Long uuid;
        private String name;
        private List<RecipeImpl> recipeList;
        private Double averageWeight;
        private Double expirationDate;
        private Double expirationDateInFridge;

        IngredientImplBuilder() {
        }

        public IngredientImpl.IngredientImplBuilder uuid(final Long uuid) {
            this.uuid = uuid;
            return this;
        }

        public IngredientImpl.IngredientImplBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public IngredientImpl.IngredientImplBuilder recipeList(final List<RecipeImpl> recipeList) {
            this.recipeList = recipeList;
            return this;
        }

        public IngredientImpl.IngredientImplBuilder averageWeight(final Double averageWeight) {
            this.averageWeight = averageWeight;
            return this;
        }

        public IngredientImpl.IngredientImplBuilder expirationDate(final Double expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public IngredientImpl.IngredientImplBuilder expirationDateInFridge(final Double expirationDateInFridge) {
            this.expirationDateInFridge = expirationDateInFridge;
            return this;
        }

        public IngredientImpl build() {
            return new IngredientImpl(this.uuid, this.name, this.recipeList, this.averageWeight, this.expirationDate, this.expirationDateInFridge);
        }

        public String toString() {
            return "IngredientImpl.IngredientImplBuilder(uuid=" + this.uuid + ", name=" + this.name + ", recipeList=" + this.recipeList + ", averageWeight=" + this.averageWeight + ", expirationDate=" + this.expirationDate + ", expirationDateInFridge=" + this.expirationDateInFridge + ")";
        }
    }

}
