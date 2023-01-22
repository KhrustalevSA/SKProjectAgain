package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.StepDescription;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stepsDescription")
public class StepDescriptionImpl implements StepDescription {
    @Id
    @Column
    private Long uuid;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "stepsDescription")
    private List<RecipeImpl> recipesList;

    public StepDescriptionImpl() {
    }

    public StepDescriptionImpl(String description) {
        this.description = description;
    }

    public StepDescriptionImpl(String description, List<RecipeImpl> recipesList) {
        this.description = description;
        this.recipesList = recipesList;
    }

    public StepDescriptionImpl(Long uuid, String description, List<RecipeImpl> recipesList) {
        this.uuid = uuid;
        this.description = description;
        this.recipesList = recipesList;
    }

    @Override
    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<RecipeImpl> getRecipesList() {
        return recipesList;
    }

    public void setRecipesList(List<RecipeImpl> recipesList) {
        this.recipesList = recipesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StepDescriptionImpl)) return false;
        StepDescriptionImpl that = (StepDescriptionImpl) o;
        return Objects.equals(getUuid(), that.getUuid()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getRecipesList(), that.getRecipesList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUuid(), getDescription(), getRecipesList());
    }

    @Override
    public String toString() {
        return "StepDescriptionImpl{" +
                "uuid=" + uuid +
                ", description='" + description + '\'' +
                ", recipesList=" + recipesList +
                '}';
    }

    private static StepDescriptionImpl.StepDescriptionImplBuilder builder() {return new StepDescriptionImpl.StepDescriptionImplBuilder();}

    public static class StepDescriptionImplBuilder {
        private Long uuid;
        private String description;
        private List<RecipeImpl> recipesList;

        StepDescriptionImplBuilder() {
        }

        public StepDescriptionImpl.StepDescriptionImplBuilder uuid(final Long uuid) {
            this.uuid = uuid;
            return this;
        }

        public StepDescriptionImpl.StepDescriptionImplBuilder description(final String description) {
            this.description = description;
            return this;
        }

        public StepDescriptionImpl.StepDescriptionImplBuilder recipesList(final List<RecipeImpl> recipesList) {
            this.recipesList = recipesList;
            return this;
        }

        public StepDescriptionImpl build() {
            return new StepDescriptionImpl(this.uuid, this.description, this.recipesList);
        }

        public String toString() {
            return "StepDescriptionImpl.StepDescriptionImplBuilder(uuid=" + this.uuid + ", description=" + this.description + ", recipesList=" + this.recipesList + ")";
        }
    }
}
