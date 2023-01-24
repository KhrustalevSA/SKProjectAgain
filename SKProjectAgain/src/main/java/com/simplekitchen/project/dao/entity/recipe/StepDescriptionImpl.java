package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.StepDescription;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "stepsDescription")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class StepDescriptionImpl implements StepDescription {
    @Id
    @Column
    private Long uuid;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "stepsDescription")
    private List<RecipeImpl> recipesList;

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
}
