package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.StepDescriptionEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * класс шагов приготовления рецепта
 * @author KhrustalevSA
 * @since 22.01.2023
 */
@Entity
@Table(name = "stepsDescription")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StepDescriptionEntityImpl implements StepDescriptionEntity {

    /**
     * поле уникального идентификатора рецепта
     */
    @Id
    @Column
    private Long id;

    /**
     * поле номера шага
     */
    @Column
    private Long stepNumber;

    /**
     * поле описания шага
     */
    @Column
    private String description;

    /**
     * поле рецепта к которому привязан шаг
     */
    @ManyToOne
    @JoinColumn(name = "steps_description")
    private RecipeEntityImpl recipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StepDescriptionEntityImpl)) return false;
        StepDescriptionEntityImpl that = (StepDescriptionEntityImpl) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getStepNumber(), that.getStepNumber()) && Objects.equals(getDescription(), that.getDescription()) && Objects.equals(getRecipe(), that.getRecipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStepNumber(), getDescription(), getRecipe());
    }
}
