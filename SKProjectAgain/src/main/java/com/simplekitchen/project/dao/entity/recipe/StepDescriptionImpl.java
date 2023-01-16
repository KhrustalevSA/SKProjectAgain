package com.simplekitchen.project.dao.entity.recipe;

import com.simplekitchen.project.dao.entity.recipe.api.StepDescription;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stepsDescription")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StepDescriptionImpl implements StepDescription {
    @Id
    @Column
    private Long uuid;

    @Column
    private String description;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "stepsDescription")
    private List<RecipeImpl> recipesList;
}
