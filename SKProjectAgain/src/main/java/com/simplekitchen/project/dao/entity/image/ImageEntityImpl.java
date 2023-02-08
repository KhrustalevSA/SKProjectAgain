package com.simplekitchen.project.dao.entity.image;

import com.simplekitchen.project.dao.entity.abztract.entity.AbstractEntity;
import com.simplekitchen.project.dao.entity.image.api.ImageEntity;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * класс сущности изображения
 * @author Khrustalevsa
 * @since 31.01.2023
 */
@Entity
@Table(name = "Images")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntityImpl extends AbstractEntity implements ImageEntity {

    /**
     * путь к изображению
     */
    @Column
    private String path;

    /**
     * путь к изображению из интернета
     */
    @Column
    private String url;

    /**
     * поле связи с рецептом
     */
    @ManyToOne
    @JoinColumn(name = "image")
    private RecipeEntityImpl recipe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageEntityImpl)) return false;
        if (!super.equals(o)) return false;
        ImageEntityImpl image = (ImageEntityImpl) o;
        return Objects.equals(getPath(), image.getPath()) && Objects.equals(getUrl(), image.getUrl()) && Objects.equals(getRecipe(), image.getRecipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPath(), getUrl(), getRecipe());
    }

}
